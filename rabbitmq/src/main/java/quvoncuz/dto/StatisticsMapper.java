package quvoncuz.dto;

import quvoncuz.enums.EventType;

public interface StatisticsMapper {

    long getCount();

    EventType getType();

}
