package com.blazarevic.demo.topology;

import com.blazarevic.demo.model.Frame;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

public class CustomTimestampExtractor implements TimestampExtractor {
    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long prevTime) {
        Frame frame = (Frame) consumerRecord.value();
        return ( ( frame.getTs() > 0 ) ? frame.getTs() : prevTime );
    }
}
