package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.utils.ExtensionString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class NameUtil {
    private Map<String, NameUtil.ObjectList> nameMap;

    public String checkNameMultiple(String instanceName) {
        return null;
    }

    public boolean IsValidIdentifier(String name, StringBuffer error) {
        return true;
    }

    public String GetUniqueName(String name) {
        return this.method_7(name, true);
    }

    public String method_7(String name, boolean param1) {
        if (!param1 && !this.getNameObjectMap().containsKey(name)) {
            return name;
        }
        return NameValid.generalValidName(name, t -> this.getNameObjectMap().containsKey(t));
    }


    public void addObjectByName(String instanceName, Object processProperty) {
    }

    public List<Object> getObjects(String name) {
        NameUtil.ObjectList objectList = this.getNameObjectMap(name).get(name.replace(" ", ""));
        ;
        if (objectList != null) {
            return objectList.getInstances();
        }
        return new ArrayList<>();
    }

    private Map<String, ObjectList> getNameObjectMap(String instanceNames) {
        if (this.nameMap == null) {
            this.nameMap = new HashMap<>();
        }
        return this.nameMap;
    }

    public void clear() {
        this.getNameObjectMap().clear();
    }

    private Map<String, ObjectList> getNameObjectMap() {
        if (this.nameMap == null) {
            this.nameMap = new HashMap<>();
        }
        return this.nameMap;
    }

    public void updateObjectName(String oldName, String newName, Object target) {
        this.removeObjectByName(ExtensionString.removeSpace(oldName), target);
        this.addObjectByName(ExtensionString.removeSpace(newName), target);

    }

    public void removeObjectByName(String name, Object target) {
        name = ExtensionString.removeSpace(name);
        NameUtil.ObjectList list = this.getNameObjectMap().get(name);
        if (list != null) {
            list.removeObject(target);
            if (list.notHaveObject()) {
                this.getNameObjectMap().remove(name);
                return;
            }
            this.getNameObjectMap().put(name, list);
        }
    }

    public class ObjectList {
        private Object lastObject;
        private List<Object> objects;

        public void addObject(Object object) {
            if (this.lastObject == null) {
                this.lastObject = object;
                return;
            }

            if (this.lastObject != object) {
                if (this.objects == null) {
                    this.objects = new ArrayList<>();
                } else if (this.objects.contains(object)) {
                    return;
                }
                this.objects.add(object);
            }
        }

        public void removeObject(Object object) {
            if (this.lastObject != object) {
                if (this.objects != null) {
                    this.objects.remove(object);
                    if (this.objects.isEmpty()) {
                        this.objects = null;
                    }
                }
                return;
            }

            if (this.objects == null) {
                this.lastObject = null;
                return;
            }

            int num = this.objects.size() - 1;
            this.lastObject = this.objects.get(num);
            if (num > 0) {
                this.objects.remove(num);
                return;
            }
            this.objects = null;
        }

        public List<Object> getInstances() {
            if (this.lastObject != null) {
                var tmp = new ArrayList<>();
                tmp.add(this.lastObject);
                return tmp;
            }

            if (this.notEmpty()) {
                return this.objects;
            }
            return new ArrayList<>();
        }

        boolean notEmpty() {
            return this.objects != null;
        }

        public boolean notHaveObject() {
            return this.lastObject == null;
        }
    }
}