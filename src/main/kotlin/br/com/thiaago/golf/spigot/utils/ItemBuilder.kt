package br.com.thiaago.golf.spigot.utils

import org.bukkit.ChatColor
import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta
import java.util.*
import java.util.function.UnaryOperator

class ItemBuilder : ItemStack {
    constructor()
    constructor(itemStack: ItemStack?) : super(itemStack)
    constructor(material: Material?) : super(material)
    constructor(material: Material?, quantity: Int, durability: Short) : super(material, quantity, durability)

    fun setAmounts(amount: Int): ItemBuilder {
        setAmount(amount)
        return this
    }

    fun setDurabilitys(durability: Short): ItemBuilder {
        setDurability(durability)
        return this
    }

    fun setDisplayName(name: String?): ItemBuilder {
        val itemMeta = itemMeta
        itemMeta.displayName = ChatColor.translateAlternateColorCodes('&', name)
        setItemMeta(itemMeta)
        return this
    }

    fun setBaseColor(color: DyeColor?): ItemBuilder {
        val bannerMeta = itemMeta as BannerMeta
        bannerMeta.baseColor = color
        itemMeta = bannerMeta
        return this
    }

    fun setLore(vararg lore: String?): ItemBuilder {
        val itemMeta = itemMeta
        itemMeta.lore = Arrays.asList(*lore)
        setItemMeta(itemMeta)
        return this
    }

    fun setLore(lore: MutableList<String>): ItemBuilder {
        val itemMeta = itemMeta
        lore.replaceAll(UnaryOperator { string: String? ->
            ChatColor.translateAlternateColorCodes(
                '&', string
            )
        })
        itemMeta.lore = lore
        setItemMeta(itemMeta)
        return this
    }

    fun addLineLore(line: String): ItemBuilder {
        val lore: MutableList<String> = ArrayList()
        if (itemMeta.hasLore()) {
            lore.addAll(itemMeta.lore)
        }
        lore.add(line)
        val itemMeta = itemMeta
        itemMeta.lore = lore
        setItemMeta(itemMeta)
        return this
    }

    fun clearLore(): ItemBuilder {
        val itemMeta = itemMeta
        itemMeta.lore = ArrayList()
        setItemMeta(itemMeta)
        return this
    }

    fun addFlag(flag: ItemFlag?): ItemBuilder {
        val itemMeta = itemMeta
        itemMeta.addItemFlags(flag)
        setItemMeta(itemMeta)
        return this
    }

    fun addFlag(vararg flag: ItemFlag?): ItemBuilder {
        val itemMeta = itemMeta
        itemMeta.addItemFlags(*flag)
        setItemMeta(itemMeta)
        return this
    }

    fun setUnbreakable(b: Boolean): ItemBuilder {
        val itemMeta = itemMeta
        itemMeta.spigot().isUnbreakable = b
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
        setItemMeta(itemMeta)
        return this
    }

    fun toItemStack(): ItemStack {
        return this
    }

}