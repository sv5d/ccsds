package eu.dariolucia.ccsds.cfdp.protocol.pdu.tlvs;

import eu.dariolucia.ccsds.cfdp.common.IntegerUtil;

public class EntityIdTLV implements TLV {

    public static final int TLV_TYPE = 0x06;

    private final Long entityId;

    private final int encodedLength;

    public EntityIdTLV(Long entityId, int octetLength) {
        if(entityId == null && octetLength > 0) {
            throw new IllegalArgumentException("No entityId supplied, but octet length is > 0: " + octetLength);
        }
        this.entityId = entityId;
        this.encodedLength = octetLength;
    }

    public EntityIdTLV(byte[] pdu, int offset, int octetLength) {
        // Starting from offset, assume that there is an encoded message with length len
        this.entityId = IntegerUtil.readInteger(pdu, offset, octetLength);
        // Encoded length
        this.encodedLength = octetLength;
    }

    public Long getEntityId() {
        return entityId;
    }

    @Override
    public int getType() {
        return TLV_TYPE;
    }

    @Override
    public int getLength() {
        return encodedLength;
    }

    @Override
    public byte[] encode(boolean withTypeLength) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String toString() {
        return "EntityIdTLV{" +
                "entityId=" + entityId +
                ", encodedLength=" + encodedLength +
                '}';
    }
}