package me.brody.mazesurvival.enchantment.persistentdata;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ScriptDataType implements PersistentDataType<byte[], Script> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<Script> getComplexType() {
        return Script.class;
    }

    @Override
    public byte[] toPrimitive(Script script, PersistentDataAdapterContext persistentDataAdapterContext) {
        return SerializationUtils.serialize(script);
    }

    @Override
    public Script fromPrimitive(byte[] bytes, PersistentDataAdapterContext persistentDataAdapterContext) {
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (Script)objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
