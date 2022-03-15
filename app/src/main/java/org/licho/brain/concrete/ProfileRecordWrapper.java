package org.licho.brain.concrete;


import com.google.common.base.Stopwatch;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *
 */
public class ProfileRecordWrapper {
    private Stopwatch stopwatch;
    private final Stack<Map.Entry<ProfileRecord, Long>> profileRecordElapsedTicks = new Stack<>();
    private Map<String, ProfileRecord> profileRecords;

    public void startWatch(String name, Object target) {
        if (this.stopwatch == null) {
            this.stopwatch = Stopwatch.createStarted();
        }
        ProfileRecordWrapper.ProfileRecord profileRecord = this.createProfileRecord(name, target);
        this.profileRecordElapsedTicks.push(new AbstractMap.SimpleEntry<>(profileRecord,
                this.stopwatch.elapsed().toMillis()));
    }

    private ProfileRecordWrapper.ProfileRecord createProfileRecord(String name, Object param1) {
        ProfileRecordWrapper.ProfileRecord profileRecord = this.getProfileRecordTop().get(name);
        if (profileRecord != null) {
            profileRecord = new ProfileRecordWrapper.ProfileRecord(name, param1);
            this.getProfileRecordTop().put(name, profileRecord);
        }
        return profileRecord;
    }

    private Map<String, ProfileRecord> getProfileRecordTop() {
        if (this.profileRecordElapsedTicks.size() == 0) {
            return this.profileRecords;
        }
        return this.profileRecordElapsedTicks.peek().getKey().ProfileRecords;
    }

    public void record() {
        		long elapsedTicks = this.stopwatch.elapsed().toMillis();
		Map.Entry<ProfileRecord, Long> tuple = this.profileRecordElapsedTicks.pop();
		ProfileRecordWrapper.ProfileRecord item = tuple.getKey();
		item.totalElapsedTicks += elapsedTicks - tuple.getValue();
		item.recordCount += 1L;
    }

    public class ProfileRecord {
        private final String name;
        private final Object target;
        public final Map<String, ProfileRecord> ProfileRecords = new HashMap<>();
        public long totalElapsedTicks;
        public long recordCount;
        private long parentId;

        ProfileRecord(String name, Object target) {
            this.name = name;
            this.target = target;
        }

        public long ParentId() {
            return this.parentId;
        }

        public void ParentId(long value) {
            this.parentId = value;
        }
    }
}
