package com.blazarevic.demo.serdes;

import com.blazarevic.demo.model.Frame;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public class AppSerdes {

    static public final class FrameSerdes extends Serdes.WrapperSerde<Frame> {
        public FrameSerdes() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(Frame.class));
        }
    }

    public static Serde<Frame> getFrameSerdes() {
        return new FrameSerdes();
    }

}
