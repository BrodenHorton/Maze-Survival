package me.brody.mazesurvival.utils;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Location;

import java.io.IOException;
import java.io.InputStream;

public class SchematicPaster {

    public static void paste(Location location, double rotation, InputStream decoration) {
        ClipboardFormat format = ClipboardFormats.findByAlias("schem");
        try {
            ClipboardReader reader = format.getReader(decoration);
            Clipboard clipboard = reader.read();

            com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(location.getWorld());
            try (EditSession roomEditSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld, -1)) {
                ClipboardHolder clipboardHolder = new ClipboardHolder(clipboard);
                clipboardHolder.setTransform(new AffineTransform().rotateY(-rotation));
                Operation operation = clipboardHolder
                        .createPaste(roomEditSession)
                        .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                        .ignoreAirBlocks(true)
                        .build();
                Operations.complete(operation);
            }
        }
        catch(WorldEditException | IOException e) {
            e.printStackTrace();
        }
    }

}
