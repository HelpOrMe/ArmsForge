import os
from PIL import Image


COLORS = [
    [255, 255, 175],  # Holy
    [130, 28, 41],    # Crimson
    [150, 0, 0],      # Damned
    [0, 127, 86],     # End  
    [215, 255, 250],  # Mithril
    [255, 156, 0],    # Gnome
    [255, 203, 107],  # Bronze
#   [44, 19, 74],     # Void
    [145, 145, 145],  # Steel
#   [199, 151, 216],  # Taum
    [255, 246, 0],    # Gold
    [100, 100, 100]   # Carbon
]

# Original: {0} * {1} / 255
# {0} and {1} equals to red, green, blue 
BLEND_FORMULA = "{0} * {1} / 215"

RUN_DIR = os.path.dirname(__file__)
INPUT_DIR = RUN_DIR + "\\input"
OUTPUR_DIR = RUN_DIR + "\\output"


def main():
    check_for_io_folders()
    for image, name in get_input_images():
        save_images(get_recolored_images_from(image, name), name);
    

def check_for_io_folders():
    for dir_path in [INPUT_DIR, OUTPUR_DIR]:
        print(f"Check for {dir_path}")
        if not os.path.exists(dir_path):
            print(f"Create {dir_path}")
            os.mkdir(dir_path)


def get_input_images():
    print("Search for images")
    for file in os.listdir(INPUT_DIR):
        if file.endswith(".png"):
            print(f"Found {file}")
            yield Image.open(f"{INPUT_DIR}\\{file}"), file.split(".")[0] 

def get_recolored_images_from(image, name):
    for color in COLORS:
        image_copy = image.copy()
        yield recolor_image(image_copy, name, color);


def recolor_image(image, name, color):
    print(f"Repainting {name} with {color}")
    width, height = image.size
    a_layer = image.split()[-1];

    for x in range(width):
        for y in range(height):
            current_color = [*image.getpixel((x, y))]
            image.putpixel((x, y), blend_colors(color, current_color));
    image.putalpha(a_layer);
    return image

def blend_colors(color_a, color_b):
    color = []
    for i in range(3):
        color.append(int(clamp255(eval(BLEND_FORMULA.format(color_a[i], color_b[i])))))
    return tuple(color)


def clamp255(value):
    return max(0, min(255, value))


def save_images(images, name):
    for i, image in enumerate(images):
        print(f"Save {name}_{i}.png")
        image.save(f"{OUTPUR_DIR}/{name}_{i}.png", "PNG")


if __name__ == "__main__":
    main()
