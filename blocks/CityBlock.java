package dyrewulf.citybiome.blocks;

import java.util.List;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dyrewulf.citybiome.DyrewulfCity;
import dyrewulf.citybiome.help.Reference;

public class CityBlock extends Block

{
	public static final Block stucco = new CityBlock().setBlockName("stucco").setBlockTextureName(Reference.MODID + ":" + "stucco").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block brushedsteel = new CityBlock().setBlockName("brushedsteel").setBlockTextureName(Reference.MODID + ":" + "brushedsteel").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block cobblestonepattern = new CityBlock().setBlockName("cobblestonepattern").setBlockTextureName(Reference.MODID + ":" + "cobblestonepattern").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block mosque = new CityBlock().setBlockName("mosque").setBlockTextureName(Reference.MODID + ":" + "mosque").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block fieldstone = new CityBlock().setBlockName("fieldstone").setBlockTextureName(Reference.MODID + ":" + "fieldstone").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block wiltern = new CityBlock().setBlockName("wiltern").setBlockTextureName(Reference.MODID + ":" + "wiltern").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block redbrick = new CityBlock().setBlockName("redbrick").setBlockTextureName(Reference.MODID + ":" + "redbrick").setCreativeTab(CreativeTabs.tabBlock);
	
	public static final Block woodTile = new CityBlock().setBlockName("woodtile").setBlockTextureName(Reference.MODID + ":" + "woodtile").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block redwoodTile = new CityBlock().setBlockName("redwoodtile").setBlockTextureName(Reference.MODID + ":" + "redwoodtile").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block fadedwoodTile = new CityBlock().setBlockName("fadedwoodtile").setBlockTextureName(Reference.MODID + ":" + "fadedwoodtile").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block tilefloor = new CityBlock().setBlockName("tilefloor").setBlockTextureName(Reference.MODID + ":" + "tilefloor").setCreativeTab(CreativeTabs.tabBlock);
	
	public static final Block asphalt = new CityBlock().setBlockName("asphalt").setBlockTextureName(Reference.MODID + ":" + "asphalt").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block weedytiles = new CityBlock().setBlockName("weedytiles").setBlockTextureName(Reference.MODID + ":" + "asphalt").setCreativeTab(CreativeTabs.tabBlock);
	
	protected CityBlock()
	{
		super(Material.rock);
		this.setHardness(1.5F);
		this.setResistance(1.5F);
	}
    
}