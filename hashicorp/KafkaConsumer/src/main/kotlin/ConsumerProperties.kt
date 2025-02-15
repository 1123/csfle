import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.config.SaslConfigs
import java.util.*

class ConsumerProperties {

    fun configureProperties() : Properties{

        val settings = Properties()
        settings.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
        settings.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer")
        settings.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test-csfle-group")
        settings.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")

        val api_key = "API Key"
        val api_secret = "API Secret"
        val broker = "CC broker"

        settings.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "$broker")
        settings.setProperty(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL")
        settings.setProperty(SaslConfigs.SASL_MECHANISM, "PLAIN")

        settings.setProperty(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username='$api_key' password='$api_secret';")

        settings.setProperty("schema.registry.url", "SR Endpoint")
        settings.setProperty("basic.auth.credentials.source", "USER_INFO")
        settings.setProperty("schema.registry.basic.auth.user.info", "<SR API Key>:<SR API Secret>")

        // Encryption
        settings.setProperty("rule.executors._default_.param.token.id", "root-token")

        return settings
    }
}