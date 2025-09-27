package me.brody.mazesurvival.brewing.persistentdata;

import org.apache.commons.lang3.SerializationUtils;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class BrewingIngredientListDataType implements PersistentDataType<byte[], BrewingIngredientList> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<BrewingIngredientList> getComplexType() {
        return BrewingIngredientList.class;
    }

    @Override
    public byte[] toPrimitive(BrewingIngredientList brewingIngredientList, PersistentDataAdapterContext persistentDataAdapterContext) {
        return SerializationUtils.serialize(brewingIngredientList);
    }

    @Override
    public BrewingIngredientList fromPrimitive(byte[] bytes, PersistentDataAdapterContext persistentDataAdapterContext) {
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (BrewingIngredientList)objectInputStream.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
