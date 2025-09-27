package me.brody.mazesurvival.enchantment.persistentdata;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class EnchantmentListDataType implements PersistentDataType<byte[], EnchantmentList> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<EnchantmentList> getComplexType() {
        return EnchantmentList.class;
    }

    @Override
    public byte[] toPrimitive(EnchantmentList customEnchantmentList, PersistentDataAdapterContext persistentDataAdapterContext) {
        return SerializationUtils.serialize(customEnchantmentList);
    }

    @Override
    public EnchantmentList fromPrimitive(byte[] bytes, PersistentDataAdapterContext persistentDataAdapterContext) {
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (EnchantmentList)objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
