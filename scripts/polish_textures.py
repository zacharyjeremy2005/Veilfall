"""Second-pass material-aware pixel art for Veilfall. Run after generate_veilfall_textures.py."""
from pathlib import Path
from PIL import Image, ImageDraw
import random
ROOT=Path("src/main/resources/assets/veilfall/textures")
def save(im, name):
 p=ROOT/name; p.parent.mkdir(parents=True,exist_ok=True); im.save(p)
def shaded(size, base, shade, accent, seed):
 r=random.Random(seed); im=Image.new("RGBA",(size,size),(*base,255)); p=im.load()
 for y in range(size):
  for x in range(size):
   if (x*5+y*7+seed)%19==0:p[x,y]=(*shade,255)
   elif r.random()<0.035:p[x,y]=(*accent,255)
 return im
# Corruption: branching, layered violet fractures instead of random speckles.
im=shaded(32,(25,17,34),(15,12,24),(75,35,90),31); d=ImageDraw.Draw(im)
for pts in [[(1,1),(7,5),(6,10),(13,14),(12,20),(19,25),(17,31)],[(30,1),(24,7),(26,12),(20,16),(22,21),(18,25)],[(1,25),(6,23),(10,27),(14,24)],[(14,2),(17,7),(15,11),(19,14)]]:
 d.line([(x+1,y+1) for x,y in pts],fill=(7,5,13,255),width=3)
 d.line(pts,fill=(112,42,132,255),width=2); d.line(pts,fill=(207,73,181,255),width=1)
for x,y in [(7,5),(6,10),(13,14),(12,20),(19,25),(24,7),(26,12),(20,16),(10,27)]:d.rectangle((x-1,y-1,x+1,y+1),fill=(246,105,210,255))
save(im,"block/corrupted_ground.png")
# Veil bark with vertical grain and spectral knots.
im=shaded(32,(31,19,39),(17,13,25),(73,38,88),72); d=ImageDraw.Draw(im)
for x in [3,8,14,21,27]:
 d.line((x,0,x-1,8,x,15,x-1,23,x,31),fill=(98,48,116,255),width=2)
 d.line((x+1,0,x,8,x+1,15,x,23,x+1,31),fill=(151,74,165,255),width=1)
d.line((2,11,5,9,8,11,11,8),fill=(211,111,207,255),width=1); save(im,"block/veil_log.png")
im=Image.new("RGBA",(32,32),(29,18,37,255));d=ImageDraw.Draw(im)
for box,col,w in [((1,1,30,30),(86,43,101,255),2),((4,4,27,27),(137,70,151,255),2),((8,8,23,23),(65,38,78,255),2),((11,11,20,20),(188,116,194,255),1)]:
 d.ellipse(box,outline=col,width=w)
d.point((16,16),fill=(242,188,238,255));save(im,"block/veil_log_top.png")
im=Image.new("RGBA",(32,32),(28,19,34,255));d=ImageDraw.Draw(im)
d.ellipse((1,1,30,30),outline=(92,51,104,255),width=2);d.ellipse((5,5,26,26),outline=(139,82,151,255),width=2);d.ellipse((10,10,21,21),outline=(183,129,190,255),width=1);d.point((16,16),fill=(220,177,228,255));save(im,"block/stripped_veil_log_top.png")
# Planed wood grain and consistent plank seams.
im=shaded(32,(46,27,53),(28,18,35),(74,39,84),73);d=ImageDraw.Draw(im)
for y in [0,8,16,24,31]:
 d.line((0,y,31,y),fill=(19,13,25,255),width=2);d.line((1,y+2,30,y+2),fill=(112,59,123,255),width=1)
for y in [4,12,20,28]:
 d.line((5,y,12,y),fill=(151,79,160,255));d.line((20,y+2,27,y+2),fill=(151,79,160,255))
save(im,"block/veil_planks.png")
# Layered canopy clusters, with teal spectral flecks.
im=Image.new("RGBA",(32,32),(23,19,32,255));d=ImageDraw.Draw(im)
for x,y,r,c in [(7,7,6,(47,28,62,255)),(19,6,7,(58,31,73,255)),(25,15,6,(39,31,56,255)),(9,20,8,(49,30,65,255)),(21,24,7,(35,34,54,255)),(16,14,7,(66,36,79,255))]:
 d.ellipse((x-r,y-r,x+r,y+r),fill=c,outline=(93,49,112,255))
for x,y in [(5,4),(12,8),(20,4),(25,12),(8,17),(14,20),(20,25),(27,23),(16,12),(22,15)]:d.rectangle((x,y,x+1,y+1),fill=(105,224,180,255))
for x,y in [(7,10),(18,8),(24,18),(11,23),(18,28)]:d.point((x,y),fill=(195,98,217,255))
save(im,"block/veil_leaves.png")
# Sapling icon/block texture with two distinct spectral leaves.
im=Image.new("RGBA",(32,32),(0,0,0,0));d=ImageDraw.Draw(im)
d.line((16,30,16,12),fill=(98,59,120,255),width=3);d.line((15,26,12,18),fill=(170,91,192,255))
d.ellipse((3,4,18,20),fill=(48,26,64,255),outline=(180,91,205,255),width=2)
d.ellipse((14,8,29,23),fill=(34,68,60,255),outline=(104,226,180,255),width=2)
d.line((8,9,13,14,9,17),fill=(225,139,234,255));d.line((19,12,23,15,20,19),fill=(190,255,225,255))
save(im,"block/veil_sapling.png")
# Replace static-noise mob skins with restrained marbling, glowing eyes and a recognizable chest rune.
palettes={"veil_stalker":((24,17,34),(255,75,192),(92,40,111)),"rotwalker":((59,54,37),(197,205,91),(105,96,56)),"ashen_husk":((76,78,89),(223,224,239),(135,135,151)),"blight_crawler":((20,43,29),(157,255,86),(55,102,54)),"hollow_sentinel":((24,24,45),(117,143,255),(62,62,112)),"riftbound":((34,18,52),(220,113,255),(93,43,128)),"flesh_marionette":((83,42,50),(255,178,188),(143,73,84)),"dread_walker":((11,11,17),(255,48,67),(59,20,36)),"veil_thrall":((48,39,65),(201,169,255),(101,79,133)),"corruption_brute":((49,22,31),(255,91,80),(121,39,51)),"the_unmaking":((7,6,13),(255,27,89),(76,20,65))}
for i,(name,(base,glow,accent)) in enumerate(palettes.items()):
 im=Image.new("RGBA",(64,64),(*base,255));p=im.load()
 for y in range(64):
  for x in range(64):
   if (x*7+y*11+i*13)%23<3:p[x,y]=(*accent,255)
   elif (x*3+y*5+i)%47==0:p[x,y]=(*glow,255)
 d=ImageDraw.Draw(im);d.rectangle((8,9,15,9),fill=(*accent,255));d.rectangle((9,10,10,11),fill=(*glow,255));d.rectangle((13,10,14,11),fill=(*glow,255));d.rectangle((10,14,13,15),fill=(3,2,8,255))
 d.line((22,21,24,24,22,27,25,30),fill=(*glow,255));d.polygon([(23,24),(25,22),(27,25),(25,28)],outline=(*glow,255))
 for x,y in [(44,22),(45,25),(40,31),(20,42),(23,48),(40,43),(43,49),(52,34)]:d.point((x,y),fill=(*glow,255))
 if name=="the_unmaking":
  d.line((8,7,11,8,13,7,16,9),fill=(255,112,163,255));d.line((20,20,23,25,20,30,25,34,22,39),fill=(*glow,255),width=2)
 save(im,f"entity/{name}.png")
print("Veilfall second-pass textures generated.")
