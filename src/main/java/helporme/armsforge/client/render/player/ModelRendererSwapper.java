package helporme.armsforge.client.render.player;

import helporme.armsforge.api.items.ITwoHandedWeapon;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ModelRendererSwapper
{
    public static void swapModelRenderersByItem(RenderPlayer renderer, Item item)
    {
        ModelBiped[] models = new ModelBiped[] { renderer.modelBipedMain, renderer.modelArmorChestplate, renderer.modelArmor };
        for (ModelBiped modelBiped : models)
        {
            if (item instanceof ITwoHandedWeapon)
            {
                swapModelOnTwoHanded(modelBiped);
            }
        }
    }

    public static void swapModelOnTwoHanded(ModelBiped modelBiped)
    {
        if (!(modelBiped.bipedRightArm instanceof RightHandTwoHandedModel))
        {
            modelBiped.bipedRightArm = new RightHandTwoHandedModel(modelBiped);
        }
        if (!(modelBiped.bipedLeftArm instanceof LeftHandTwoHandedModel))
        {
            modelBiped.bipedLeftArm = new LeftHandTwoHandedModel(modelBiped);
        }

        RightHandTwoHandedModel rightHand = (RightHandTwoHandedModel)modelBiped.bipedRightArm;
        rightHand.useSwappedRender = true;

        LeftHandTwoHandedModel leftHand = (LeftHandTwoHandedModel)modelBiped.bipedLeftArm;
        leftHand.useSwappedRender = true;
    }

    public static void turnOffTwoHandedSwappedRenderers(RenderPlayer renderer)
    {
        if (isEnabledSwappedRenderersExists(renderer))
        {
            turnOffSwappedRenderers(getHandModelRenderersFrom(renderer));
        }
    }

    public static boolean isEnabledSwappedRenderersExists(RenderPlayer renderer)
    {
        for (ModelRenderer modelRenderer : getHandModelRenderersFrom(renderer.modelBipedMain))
        {
            if (modelRenderer instanceof SwappedModelRenderer)
            {
                SwappedModelRenderer swappedModel = ((SwappedModelRenderer)modelRenderer);
                if (swappedModel.useSwappedRender)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public static void turnOffSwappedRenderers(ModelRenderer... modelRenderers)
    {
        for (ModelRenderer modelRenderer : modelRenderers)
        {
            if (modelRenderer instanceof SwappedModelRenderer)
            {
                SwappedModelRenderer swappedModel = ((SwappedModelRenderer)modelRenderer);
                swappedModel.useSwappedRender = false;
            }
        }
    }

    public static ModelRenderer[] getHandModelRenderersFrom(RenderPlayer renderer)
    {
        Set<ModelRenderer> modelRenderers = new HashSet<>();
        ModelBiped[] models = new ModelBiped[] { renderer.modelBipedMain, renderer.modelArmorChestplate, renderer.modelArmor };

        for (ModelBiped modelBiped : models)
        {
            modelRenderers.addAll(Arrays.asList(getHandModelRenderersFrom(modelBiped)));
        }
        return modelRenderers.toArray(new ModelRenderer[0]);
    }

    public static ModelRenderer[] getHandModelRenderersFrom(ModelBiped modelBiped)
    {
        return new ModelRenderer[] { modelBiped.bipedRightArm, modelBiped.bipedLeftArm };
    }
}
