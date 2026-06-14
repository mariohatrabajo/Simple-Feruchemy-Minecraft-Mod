package net.marioio.simpleferuchemy.network;
import net.marioio.simpleferuchemy.item.ModItems;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record UpdateBookTextPacket(int slot, List<String> pages) {

    // Serialización
    public static void encode(UpdateBookTextPacket pkt, FriendlyByteBuf buf) {
        buf.writeInt(pkt.slot);
        buf.writeInt(pkt.pages.size());
        for(int i = 0; i < pkt.pages.size(); i++) {
            buf.writeUtf(pkt.pages.get(i));
        }
    }

    // Deserialización
    public static UpdateBookTextPacket decode(FriendlyByteBuf buf) {
        int slot = buf.readInt();

        int pageCount = buf.readInt();
        List<String> pages = new ArrayList<>();

        for (int i = 0; i < pageCount; i++) {
            pages.add(buf.readUtf(32767));
        }

        return new UpdateBookTextPacket(slot, pages);
    }

    // Lógica en el servidor
    public static void handle(UpdateBookTextPacket pkt, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            var player = ctx.get().getSender();
            if (player == null) return;

            ItemStack stack = player.getInventory().getItem(pkt.slot);
            if (stack.isEmpty()) return;

            // Asegurarse de que sea el libro correcto
            if (!(stack.is(ModItems.COPPER_RING.get()) || stack.is(ModItems.COPPER_BRACELET.get()))) return;

            var tag = stack.getOrCreateTag();
            var pages = new net.minecraft.nbt.ListTag();
            for(int i = 0; i < pkt.pages.size(); i++) {
                pages.add(net.minecraft.nbt.StringTag.valueOf(pkt.pages.get(i)));
            }
            tag.put("pages", pages);

            player.getInventory().setItem(pkt.slot, stack);
        });
        ctx.get().setPacketHandled(true);
    }
}