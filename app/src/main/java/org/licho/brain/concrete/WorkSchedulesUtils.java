package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.utils.simu.system.DateTime;

/**
 *
 */
public class WorkSchedulesUtils {
    private final WorkSchedules workSchedules;
    private final DayPatterns dayPatterns;

    public WorkSchedulesUtils(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.workSchedules = new WorkSchedules(intelligentObjectDefinition);
        this.dayPatterns = new DayPatterns(intelligentObjectDefinition, null);
    }

    public WorkSchedules getWorkSchedules() {
        return this.workSchedules;
    }

    public DayPatterns DayPatterns() {
        return this.dayPatterns;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                           IntelligentObjectDefinition intelligentObjectDefinition) {
        return this.getWorkSchedules().readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition) || this.DayPatterns().readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition);
    }

    public void setScheduleTime() {
        DayPattern dayPattern = this.DayPatterns().AddNew();
        dayPattern.Name("StandardDay");
        dayPattern.Description("Standard 8-5 Work Day");
        WorkPeriod workPeriod = dayPattern.WorkPeriods().AddNew();
        workPeriod.EndTime(new DateTime(1, 1, 1, 12, 0, 0));
        workPeriod.StartTime(new DateTime(1, 1, 1, 8, 0, 0));
        workPeriod.Value("1");
        workPeriod = dayPattern.WorkPeriods().AddNew();
        workPeriod.EndTime(new DateTime(1, 1, 1, 17, 0, 0));
        workPeriod.StartTime(new DateTime(1, 1, 1, 13, 0, 0));
        workPeriod.Value("1");
        WorkSchedule workSchedule = this.getWorkSchedules().AddNew();
        workSchedule.Name("StandardWeek");
        workSchedule.Description("Standard Work Week Schedule");
        workSchedule.StartDate(new DateTime(2011, 1, 3));
        workSchedule.Days(7);
        for (int i = 0; i < 5; i++) {
            workSchedule.DayPatternRefs().Insert(i, new DayPatternRef(dayPattern.Parent(), dayPattern));
        }
    }
}
