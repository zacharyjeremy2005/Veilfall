#!/usr/bin/env python3
"""Generate Veilfall's original pixel-art textures for Minecraft 1.20.1."""
from pathlib import Path
import random
from PIL import Image, ImageDraw

ROOT = Path("src/main/resources/assets/veilfall/textures")
random.seed(731904)

def save(image, relative):
    target = ROOT / relative
    target.parent.mkdir(parents=True, exist_ok=True)
    image.save(target)

def noisy_block(base, specks, seed_offset=0, size=16):
    rng = random.Random(731904 + seed_offset)
    im = Image.new("RGBA", (size, size), (*base, 255))
    px = im.load()
    for y in range(size):
        for x in range(size):
            if rng.random() < 0.47:
                c = rng.choice(specks)
                px[x, y] = (*c, 255)
    return im

# Corruption looks like cracked, violet-black stone rather than recolored netherrack.
im = noisy_block((24, 17, 34), [(44, 27, 58), (63, 28, 73), (35, 31, 51), (91, 35, 94)], 1)
d = ImageDraw.Draw(im)
for line in [[(2, 1), (5, 4), (4, 7), (8, 10)], [(13, 2), (10, 5), (12, 8), (9, 12)], [(1, 13), (5, 11), (7, 14)]]:
    d.line(line, fill=(190, 54, 160, 255), width=1)
for x, y in [(5, 4), (4, 7), (10, 5), (12, 8), (7, 14)]:
    im.putpixel((x, y), (246, 105, 210, 255))
save(im, "block/corrupted_ground.png")

# Veilite ore: slate host rock with a luminous cyan-purple seam.
for name, base, dark, bright in [
    ("veilite_ore", (47, 48, 61), (23, 31, 52), (80, 244, 224)),
    ("deepslate_veilite_ore", (29, 29, 42), (13, 17, 30), (181, 94, 255)),
]:
    im = noisy_block(base, [dark, (base[0]+8, base[1]+7, base[2]+9), (38, 40, 54)], 8 if name == "veilite_ore" else 9)
    d = ImageDraw.Draw(im)
    d.line([(2, 4), (5, 3), (7, 6), (10, 5), (13, 8)], fill=bright, width=2)
    d.point((4, 9), fill=bright); d.point((11, 12), fill=bright); d.point((7, 13), fill=(230, 190, 255, 255))
    save(im, f"block/{name}.png")

# Restoration table top: a pale work surface with a contained violet ring.
im = noisy_block((94, 79, 105), [(78, 66, 91), (125, 108, 136), (67, 56, 82)], 14)
d = ImageDraw.Draw(im)
d.rectangle((3, 3, 12, 12), outline=(88, 241, 218, 255), width=1)
d.rectangle((5, 5, 10, 10), outline=(205, 119, 255, 255), width=1)
d.point((7, 7), fill=(245, 255, 239, 255))
save(im, "block/veil_restoration_table.png")

def transparent_icon():
    return Image.new("RGBA", (16, 16), (0, 0, 0, 0))

def item_ingot():
    im = transparent_icon(); d = ImageDraw.Draw(im)
    d.polygon([(3, 5), (10, 3), (14, 6), (12, 12), (5, 13), (2, 9)], fill=(60, 218, 200, 255), outline=(20, 61, 76, 255))
    d.polygon([(3, 5), (10, 3), (12, 6), (5, 8)], fill=(194, 255, 239, 255))
    d.line([(5, 9), (11, 7), (12, 10)], fill=(163, 108, 255, 255))
    return im

def item_shard():
    im = transparent_icon(); d = ImageDraw.Draw(im)
    d.polygon([(8, 1), (12, 6), (10, 14), (6, 12), (4, 6)], fill=(95, 244, 224, 255), outline=(35, 89, 125, 255))
    d.polygon([(8, 2), (8, 10), (5, 6)], fill=(218, 255, 255, 255))
    d.polygon([(8, 10), (11, 6), (9, 13)], fill=(185, 99, 255, 255))
    return im

def weapon(kind):
    im = transparent_icon(); d = ImageDraw.Draw(im)
    dark=(28, 23, 47, 255); metal=(88, 238, 221, 255); shine=(218, 255, 247, 255); purple=(174, 89, 255, 255)
    if kind == "sword":
        d.polygon([(12, 1), (14, 3), (7, 11), (5, 9)], fill=metal, outline=dark); d.line([(12, 2), (7, 10)], fill=shine)
        d.line([(4, 8), (9, 12)], fill=purple, width=2); d.line([(5, 12), (2, 15)], fill=dark, width=3)
    elif kind == "pickaxe":
        d.line([(4, 13), (12, 4)], fill=(106, 64, 133, 255), width=3)
        d.line([(2, 4), (6, 2), (10, 3), (14, 5)], fill=metal, width=3); d.point((3, 4), fill=shine)
    elif kind == "axe":
        d.line([(4, 13), (12, 4)], fill=(106, 64, 133, 255), width=3)
        d.polygon([(3, 2), (9, 3), (13, 6), (9, 8), (5, 6)], fill=metal, outline=dark); d.line([(4, 3), (8, 4)], fill=shine)
    elif kind == "shovel":
        d.line([(4, 13), (11, 5)], fill=(106, 64, 133, 255), width=3); d.polygon([(9, 2), (14, 4), (12, 9), (8, 7)], fill=metal, outline=dark)
    elif kind == "hoe":
        d.line([(4, 13), (11, 5)], fill=(106, 64, 133, 255), width=3); d.line([(7, 4), (10, 2), (14, 3)], fill=metal, width=3)
    return im

def armor(kind):
    im=transparent_icon(); d=ImageDraw.Draw(im)
    metal=(55, 62, 91, 255); edge=(24, 21, 41, 255); glow=(95, 244, 224, 255); purple=(173, 83, 255, 255)
    if kind=="helmet":
        d.polygon([(3, 5),(5, 2),(11, 2),(14, 5),(13, 12),(9, 10),(4, 12)],fill=metal,outline=edge);d.rectangle((5,6,11,7),fill=glow);d.point((8,4),fill=purple)
    elif kind=="chestplate":
        d.polygon([(4,2),(7,3),(9,3),(12,2),(15,5),(12,8),(12,14),(4,14),(4,8),(1,5)],fill=metal,outline=edge);d.line((8,4,8,12),fill=glow,width=2);d.point((8,7),fill=purple)
    elif kind=="leggings":
        d.polygon([(3,2),(13,2),(12,7),(11,14),(8,14),(7,8),(5,14),(2,14)],fill=metal,outline=edge);d.line((8,4,8,11),fill=glow);d.point((5,6),fill=purple)
    elif kind=="boots":
        d.polygon([(3,2),(7,2),(7,9),(5,12),(1,12),(2,8)],fill=metal,outline=edge);d.polygon([(9,2),(13,2),(14,8),(15,12),(10,12),(9,9)],fill=metal,outline=edge);d.line((2,11,5,11),fill=glow);d.line((11,11,14,11),fill=glow)
    return im

# Item textures
save(item_ingot(), "item/veilite_ingot.png")
save(item_shard(), "item/veil_crystal.png")
save(item_shard().transpose(Image.Transpose.FLIP_LEFT_RIGHT), "item/raw_veilite.png")
for kind in ("sword","pickaxe","axe","shovel","hoe"):
    save(weapon(kind), f"item/veilite_{kind}.png")
for kind in ("helmet","chestplate","leggings","boots"):
    save(armor(kind), f"item/veilite_{kind}.png")
# The upgrade template is a small amethyst-and-metal glyph.
im=transparent_icon(); d=ImageDraw.Draw(im); d.rectangle((3,3,12,12),outline=(70,53,93,255),width=2); d.polygon([(8,4),(11,8),(8,12),(5,8)],fill=(169,94,255,255)); d.point((8,8),fill=(219,255,249,255)); save(im,"item/veilite_upgrade_template.png")
# Bow/crossbow placeholder art is still original and readable at inventory scale.
im=transparent_icon(); d=ImageDraw.Draw(im); d.arc((2,1,13,14),270,90,fill=(95,244,224,255),width=2); d.line((8,2,8,14),fill=(182,108,255,255)); save(im,"item/veilite_bow.png")
im=transparent_icon(); d=ImageDraw.Draw(im); d.rectangle((3,6,12,10),fill=(57,51,81,255),outline=(24,21,41,255)); d.polygon([(3,5),(8,2),(13,5)],fill=(95,244,224,255)); d.line((8,2,8,14),fill=(220,255,250,255)); save(im,"item/veilite_crossbow.png")

# Armor worn texture layers use the standard 64x32 Minecraft armor UV layout.
for layer in (1,2):
    im=Image.new("RGBA",(64,32),(0,0,0,0)); p=im.load()
    for y in range(32):
        for x in range(64):
            # Paint only armor islands; transparent background preserves the vanilla UV silhouette.
            regions = [(8,0,23,7),(0,8,31,19),(4,20,27,31),(32,0,47,15),(32,16,63,31)]
            if any(x1<=x<=x2 and y1<=y<=y2 for x1,y1,x2,y2 in regions):
                if (x+y)%7==0:p[x,y]=(95,244,224,255)
                elif (x*3+y)%11==0:p[x,y]=(178,90,255,255)
                else:p[x,y]=(45,48,74,255)
    save(im,f"models/armor/veilite_layer_{layer}.png")

# Distinct, dark silhouettes and glowing eyes for the eleven Veilfall threats.
mobs = {
 "veil_stalker":((31,22,43),(255,65,185)),
 "rotwalker":((72,67,47),(176,211,86)),
 "ashen_husk":((105,105,111),(220,220,230)),
 "blight_crawler":((29,57,35),(143,255,78)),
 "hollow_sentinel":((31,31,55),(119,140,255)),
 "riftbound":((43,22,66),(210,104,255)),
 "flesh_marionette":((105,54,62),(255,178,186)),
 "dread_walker":((18,18,24),(255,45,65)),
 "veil_thrall":((66,55,86),(195,157,255)),
 "corruption_brute":((63,30,39),(255,83,78)),
 "the_unmaking":((12,10,18),(255,25,83)),
}
for index,(name,(base,eye)) in enumerate(mobs.items()):
    rng=random.Random(900+index)
    im=Image.new("RGBA",(64,64),(*base,255)); p=im.load()
    for y in range(64):
        for x in range(64):
            if rng.random()<0.18:
                delta=rng.choice([-18,-10,10,17]);p[x,y]=(max(0,min(255,base[0]+delta)),max(0,min(255,base[1]+delta)),max(0,min(255,base[2]+delta)),255)
    d=ImageDraw.Draw(im)
    # Minecraft zombie head-front eye/mouth region; a luminous face helps distinguish each silhouette.
    d.rectangle((9,10,10,11),fill=(*eye,255));d.rectangle((13,10,14,11),fill=(*eye,255))
    d.rectangle((10,14,13,15),fill=(7,5,12,255))
    d.point((8,8),fill=(230,255,255,255));d.point((15,8),fill=(*eye,255))
    # Vein-like accents along the body texture.
    d.line((20,20,22,24,20,28),fill=(*eye,255),width=1)
    d.line((43,20,41,24,44,27),fill=(*eye,255),width=1)
    save(im,f"entity/{name}.png")
print("Generated Veilfall textures.")
