package eu.dariolucia.ccsds.cfdp.mib;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement(name = "mib", namespace = "http://dariolucia.eu/ccsds/cfdp/mib")
@XmlAccessorType(XmlAccessType.FIELD)
public class Mib {

    /**
     * This method loads a {@link Mib} object from an {@link InputStream}.
     *
     * @param in the input stream, to read from
     * @return the loaded definition
     * @throws IOException in case of problems while processing the input stream
     */
    public static Mib load(InputStream in) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(Mib.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Mib) unmarshaller.unmarshal(in);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    /**
     * This method serialises the provided {@link Mib} object to the provided
     * {@link OutputStream}.
     *
     * @param d   the definition to serialise
     * @param out the output stream
     * @throws IOException in case of problems while serialising or writing to the stream
     */
    public static void save(Mib d, OutputStream out) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(Mib.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(d, out);
        } catch (JAXBException e) {
            throw new IOException(e);
        }
    }

    @XmlElement(name = "local_entity")
    private LocalEntityConfigurationInformation localEntity;

    @XmlElementWrapper(name = "remote_entities")
    @XmlElement(name = "remote_entity")
    private List<RemoteEntityConfigurationInformation> remoteEntities = new LinkedList<>();

    public LocalEntityConfigurationInformation getLocalEntity() {
        return localEntity;
    }

    public Mib setLocalEntity(LocalEntityConfigurationInformation localEntity) {
        this.localEntity = localEntity;
        return this;
    }

    public List<RemoteEntityConfigurationInformation> getRemoteEntities() {
        return remoteEntities;
    }

    public Mib setRemoteEntities(List<RemoteEntityConfigurationInformation> remoteEntities) {
        this.remoteEntities = remoteEntities;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder("Mib{\n" +
                "localEntity=" + localEntity + "\n");
        for(RemoteEntityConfigurationInformation ri : this.remoteEntities) {
            toReturn.append(ri).append("\n");
        }
        toReturn.append('}');
        return toReturn.toString();
    }
}