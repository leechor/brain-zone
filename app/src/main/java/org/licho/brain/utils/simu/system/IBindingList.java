package org.licho.brain.utils.simu.system;

import java.beans.PropertyDescriptor;

/**
 *
 */
public interface IBindingList {
    boolean AllowNew();

    <T> T AddNew();

    boolean AllowEdit();

    boolean AllowRemove();

    boolean SupportsChangeNotification();

    boolean SupportsSearching();

    boolean SupportsSorting();

    boolean IsSorted();

    PropertyDescriptor SortProperty();

//    ListSortDirection SortDirection();

//    event ListChangedEventHandler ListChanged;

    void AddIndex(PropertyDescriptor property);

//    void ApplySort(PropertyDescriptor property, ListSortDirection direction);

    int Find(PropertyDescriptor property, Object key);

    void RemoveIndex(PropertyDescriptor property);

    void RemoveSort();
}
