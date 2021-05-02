import java.io.Serializable;

/**
 *
 *
 * @author Kenny Park
 * @version 
 */
public class Packet implements Serializable {
    enum PacketType {
        POST,
        PROFILE,
        COMMENT,
        POST_HASHTABLE,
        PROFILE_HASHTABLE,
        NEW_PROFILE_ID_QUERY,
        NEW_PROFILE_ID_RESPONSE,
        DELETE_POST_QUERY,
        DELETE_PROFILE_QUERY,
        DELETE_COMMENT_QUERY,
    }

    private final PacketType packetType;
    private final Object object;

    public Packet(PacketType packetType, Object object) {
        this.packetType = packetType;
        this.object = object;
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public Object getObject() {
        return object;
    }
}
