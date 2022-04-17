package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.event.EventHandler;
import org.licho.brain.event.IEvent;
import org.licho.brain.utils.simu.system.AddingNewEventArgs;
import org.licho.brain.utils.simu.system.AddingNewEventHandler;
import org.licho.brain.utils.simu.system.IBindingList;
import org.licho.brain.utils.simu.system.ListChangedEventArgs;
import org.licho.brain.utils.simu.system.ListChangedEventHandler;
import org.licho.brain.utils.simu.system.ListChangedType;
import org.licho.brain.utils.simu.system.PropertyChangedEventArgs;
import org.licho.brain.utils.simu.system.PropertyChangedEventHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BindingList<T> implements IBindingList {
    private int addNewPos = -1;
    private boolean raiseListChangedEvents = true;
    private boolean raiseItemChangedEvents = false;

    private int lastChangeIndex = -1;
    private boolean allowNew = true;
    private boolean allowEdit = true;
    private boolean allowRemove = true;
    private boolean userSetAllowNew = false;

    private PropertyChangedEventHandler propertyChangedEventHandler = null;
    private AddingNewEventHandler onAddingNew;
    private ListChangedEventHandler onListChanged;
    public List<T> values = new ArrayList<>();
    private AddingNewEventHandler _onAddingNew;
    public Class<T> cl;

    private void Initialize() {
        this.allowNew = ItemTypeHasDefaultConstructor();

        this.raiseItemChangedEvents = true;
        for (T item : this.values) {
            this.HookPropertyChanged(item);
        }
    }

    public List<T> getValues() {
        return this.values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public void addAddingNew(AddingNewEventHandler value) {
        boolean allowNewWasTrue = this.AllowNew();
        this.onAddingNew.subscribe(value);
        if (allowNewWasTrue != this.AllowNew()) {
            this.FireListChanged(ListChangedType.Reset, -1);
        }
    }

    public void removeAddingNew(AddingNewEventHandler value) {
        boolean allowNewWasTrue = this.AllowNew();
        this.onAddingNew.unSubscribe(value);
        if (allowNewWasTrue != this.AllowNew()) {
            this.FireListChanged(ListChangedType.Reset, -1);
        }
    }

    public void addListChanged(IEvent<ListChangedEventArgs> value) {
        EventHandler.subscribe(this.onListChanged, value);
    }

    public void removeListChanged(IEvent<ListChangedEventArgs> value) {
        EventHandler.unSubscribe(this.onListChanged, value);
    }

    @Override
    public boolean AllowNew() {
        //If the user set AllowNew, return what they set.  If we have a default constructor, allowNew will be
        //true and we should just return true.
        if (this.userSetAllowNew || this.allowNew) {
            return this.allowNew;
        }
        //Even if the item doesn't have a default constructor, the user can hook AddingNew to provide an item.
        //If there's a handler for this, we should allow new.
        return this.AddingNewHandled();
    }

    public void AllowNew(boolean value) {
        if (value) {
            this.cl = this.ItemType();
            if (this.cl == null) {
                return;
            }
        }

        boolean oldAllowNewValue = this.AllowNew();
        userSetAllowNew = true;
        //Note that we don't want to set allowNew only if AllowNew didn't match value,
        //since AllowNew can depend on onAddingNew handler
        this.allowNew = value;
        if (oldAllowNewValue != value) {
            this.FireListChanged(ListChangedType.Reset, -1);
        }
    }

    protected Class<T> ItemType() {
        return null;
    }

    private void FireListChanged(ListChangedType type, int index) {
        if (this.raiseListChangedEvents) {
            OnListChanged(new ListChangedEventArgs(type, index));
        }
    }


    private boolean AddingNewHandled() {
        return onAddingNew != null;
    }


    private void HookPropertyChanged(T item) {
        if (item instanceof INotifyPropertyChanged) {
            if (propertyChangedEventHandler == null) {
                this.propertyChangedEventHandler.subscribe(this::Child_PropertyChanged);
            }
           INotifyPropertyChanged.PropertyChanged.subscribe(this.propertyChangedEventHandler);
        }
    }

    private void UnhookPropertyChanged(T item) {
        if (item instanceof INotifyPropertyChanged && this.propertyChangedEventHandler != null) {
            INotifyPropertyChanged.PropertyChanged.unSubscribe(this.propertyChangedEventHandler);
        }
    }

    protected void clear() {
        this.EndNew(this.addNewPos);
        if (this.raiseItemChangedEvents) {
            for (T item : this.values) {
                UnhookPropertyChanged(item);
            }
        }

        this.values.clear();
        this.FireListChanged(ListChangedType.Reset, -1);
    }

    public void CancelNew(int itemIndex) {
        if (addNewPos >= 0 && addNewPos == itemIndex) {
            RemoveItem(addNewPos);
            addNewPos = -1;
        }
    }

    public void EndNew(int itemIndex) {
        if (this.addNewPos >= 0 && this.addNewPos == itemIndex) {
            this.addNewPos = -1;
        }
    }

    @SuppressWarnings("unchecked")
    void Child_PropertyChanged(Object sender, PropertyChangedEventArgs e) {
        if (this.RaiseListChangedEvents()) {
            if (sender == null || e == null || Strings.isNullOrEmpty(e.PropertyName())) {
                // Fire reset event (per INotifyPropertyChanged spec)
                ResetBindings();
            } else {
                // The change event is broken should someone pass an item to us that is not
                // of type T.  Still, if they do so, detect it and ignore.  It is an incorrect
                // and rare enough occurrence that we do not want to slow the mainline path
                // with "is" checks.
                T item;

                try {
                    item = (T) sender;
                } catch (IllegalArgumentException ignored) {
                    ResetBindings();
                    return;
                }

                // Find the position of the item.  This should never be -1.  If it is,
                // somehow the item has been removed from our list without our knowledge.
                int pos = lastChangeIndex;

                if (pos < 0 || pos >= this.values.size() || !this.get(pos).equals(item)) {
                    pos = this.values.indexOf(item);
                    lastChangeIndex = pos;
                }

                if (pos == -1) {
                    UnhookPropertyChanged(item);
                    ResetBindings();
                } else {

                    // Create event args.  If there was no matching property descriptor,
                    // we raise the list changed anyway.
                    // TODO: 2022/1/26
                    ListChangedEventArgs args = new ListChangedEventArgs(ListChangedType.ItemChanged, pos, null);

                    // Fire the ItemChanged event
                    this.OnListChanged(args);
                }
            }
        }
    }

    protected void OnListChanged(ListChangedEventArgs args) {
        if (this.onListChanged != null) {
            this.onListChanged.fire(this, args);
        }
    }


    private boolean ItemTypeHasDefaultConstructor() {
        return true;
    }

    protected void AllowEdit(boolean b) {
    }

    protected void AllowRemove(boolean b) {
    }

    public boolean RaiseListChangedEvents() {
        return this.raiseListChangedEvents;
    }

    public void RaiseListChangedEvents(boolean value) {
        if (this.raiseListChangedEvents == value)
            return;
        this.raiseListChangedEvents = value;
    }

    public void ResetBindings() {
        // TODO: 2021/12/17
    }

    public void ClearItems() {
    }

    public int size() {
        return this.values.size();
    }

    public List<T> reverse() {
        // TODO: 2022/1/19
        return this.values;
    }

    protected Object AddNewCore() {
        var tmp = this.FireAddingNew();
        Object obj = tmp == null ? this.createInstance() : tmp;
        this.Add((T) obj);
        return obj;
    }

    private T createInstance() {
        if (cl == null) {
            return null;
        }

        try {
            return cl.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Object FireAddingNew() {
        AddingNewEventArgs e = new AddingNewEventArgs((Object) null);
        this.OnAddingNew(e);
        return e.NewObject();
    }

    protected void OnAddingNew(AddingNewEventArgs e) {
        AddingNewEventHandler onAddingNew = this._onAddingNew;
        if (onAddingNew == null)
            return;
        onAddingNew.fire((Object) this, e);
    }


    public void Insert(int index, T item) {
        this.InsertItem(index, item);
    }

    protected void Add(T item) {
        int count = this.values.size();
        this.InsertItem(count, item);
    }

    public void add(T item) {
        this.values.add(item);
    }

    public void Remove(int index) {
        this.values.remove(index);
    }

    public boolean Remove(T item) {
        int index = this.values.indexOf(item);
        if (index < 0)
            return false;
        this.RemoveItem(index);
        return true;
    }

    protected void InsertItem(int index, T item) {
        this.EndNew(this.addNewPos);
        this.values.add(index, item);
        if (this.raiseItemChangedEvents) {
            this.HookPropertyChanged(item);
        }
        this.FireListChanged(ListChangedType.ItemAdded, index);
    }

    protected void RemoveItem(int index) {
        // Need to all RemoveItem if this on the AddNew item
        if (!this.allowRemove && !(this.addNewPos >= 0 && this.addNewPos == index)) {
            throw new IndexOutOfBoundsException();
        }

        this.EndNew(addNewPos);

        if (this.raiseItemChangedEvents) {
            UnhookPropertyChanged(this.get(index));
        }

        this.values.remove(index);
        FireListChanged(ListChangedType.ItemDeleted, index);
    }

    protected void SetItem(int index, T item) {
        if (this.raiseItemChangedEvents) {
            UnhookPropertyChanged(this.get(index));
        }
        this.values.set(index, item);
        if (this.raiseItemChangedEvents) {
            HookPropertyChanged(item);
        }
        FireListChanged(ListChangedType.ItemChanged, index);
    }

    @Override
    public <T> T AddNew() {
        Object obj = this.AddNewCore();
        this.addNewPos = obj != null ? this.values.indexOf((T) obj) : -1;
        return (T) obj;
    }


    @Override
    public boolean AllowEdit() {
        return false;
    }

    @Override
    public boolean AllowRemove() {
        return false;
    }

    @Override
    public boolean SupportsChangeNotification() {
        return false;
    }

    @Override
    public boolean SupportsSearching() {
        return false;
    }

    @Override
    public boolean SupportsSorting() {
        return false;
    }

    @Override
    public boolean IsSorted() {
        return false;
    }

    @Override
    public PropertyDescriptor SortProperty() {
        return null;
    }

    @Override
    public void AddIndex(PropertyDescriptor property) {

    }

    @Override
    public int Find(PropertyDescriptor property, Object key) {
        return 0;
    }

    @Override
    public void RemoveIndex(PropertyDescriptor property) {

    }

    @Override
    public void RemoveSort() {

    }

    public T get(int index) {
        return this.values.get(index);
    }

}
