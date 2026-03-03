import org.eclipse.paho.client.mqttv3.*;

import java.util.Scanner;
import java.util.UUID;

/**
 * @author Czarkowska
 * @author Poleszak
 */
public class MqttChat {
    private static final String BROKER = "tcp://broker.emqx.io:1883";
    private static final String TOPIC = "MQTT chat - lab OPA";
    MqttChat() throws MqttException, InterruptedException {
        System.out.println("Broker: " + BROKER);

        String publisherId = UUID.randomUUID().toString();
        System.out.println("My ID: " + publisherId);

        IMqttClient publisher = new MqttClient(BROKER, publisherId);
        System.out.println("Publisher: " + publisher);

        MqttConnectOptions options = new MqttConnectOptions();
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);
        System.out.println("Connect options: " + options);

        publisher.connect(options);
        if (!publisher.isConnected()) {
            System.out.println("NOT Connected!");
            publisher.close();
            return;
        }
        System.out.println("Connected!");

        publisher.subscribe(TOPIC, new IMqttMessageListener() {
            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println(new String(message.getPayload()));
            }
        });

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your nickname: ");
        String nickname = scanner.nextLine();

        while (true) {
            String message = scanner.nextLine();
            if (message.equals("exit")) break;
            try {
                String fullMessage = nickname + ": " + message;
                publisher.publish(TOPIC, new MqttMessage(fullMessage.getBytes()));
            } catch (MqttException e) {
                System.out.println(e.getMessage());
            }
        }
        publisher.disconnect();
        publisher.close();
    }

    public static void main(String[] args) throws MqttException, InterruptedException {
        MqttChat chat = new MqttChat();
    }
}
