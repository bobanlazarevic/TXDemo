import com.blazarevic.demo.main.AppConfigs;
import com.blazarevic.demo.model.Frame;
import com.blazarevic.demo.topology.CustomAppTopology;
import org.apache.kafka.streams.*;
import org.junit.jupiter.api.*;

import java.util.Properties;

import static com.blazarevic.demo.serdes.AppSerdes.getFrameSerdes;
import static org.apache.kafka.common.serialization.Serdes.String;

class LoadingFrameTest {
    private static TopologyTestDriver topologyTestDriver;
    private static TestInputTopic input_topic;

    @BeforeAll
    static void setUpAll() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.applicationLoadingID);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
        props.put(StreamsConfig.STATE_DIR_CONFIG, AppConfigs.stateStoreName);

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        CustomAppTopology.withLoadingBuilder(streamsBuilder);
        Topology topology = streamsBuilder.build();

        topologyTestDriver = new TopologyTestDriver(topology, props);

        input_topic = topologyTestDriver.createInputTopic(AppConfigs.inputTopic,
                String().serializer(),
                getFrameSerdes().serializer()
        );
    }

    @Test
    void test_frame_loading() {
        input_topic.pipeInput(null, new Frame().withUID("AAA").withTS(1625752362L));
        input_topic.pipeInput("key1", new Frame().withUID("BBB").withTS(1625752362L));
        input_topic.pipeInput("key2", new Frame().withUID("CCC").withTS(1625752362L));
    }

    @AfterAll
    static void cleanUpAll() {
        topologyTestDriver.close();
    }
}