import tkinter as tk
from tkinter import ttk, messagebox
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
import numpy as np
import skimage.io as io
from skimage import filters, util
import os

class ImageProcessingApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Image Processing Lab - Image Denoising App")

        # Image variables
        self.original_image = None
        self.corrupted_image = None
        self.filtered_image = None
        self.noise_type = tk.StringVar(value="Gaussian")
        self.filter_type = tk.StringVar(value="Gaussian")
        self.filter_size = tk.StringVar(value="3x3")
     
        self.fig, self.ax = plt.subplots(1, 3, figsize=(18, 6))  # Adjust figure size

        # Image selection components
        self.image_label = ttk.Label(self.root, text="Image:")
        self.image_label.grid(row=0, column=2, columnspan=1)

        folder_name = "images"
        self.image_name = tk.StringVar()
        self.image_name.set(os.path.join(folder_name, "coins.jpg"))
        self.image_dropdown = ttk.Combobox(self.root, textvariable=self.image_name,
                                           values=[os.path.join(folder_name, "coins.jpg"),
                                                   os.path.join(folder_name, "peppers.png"),
                                                   os.path.join(folder_name, "saturn.png"),
                                                   os.path.join(folder_name, "rice.png")])
        self.image_dropdown.grid(row=0, column=3)

        self.load_image_button = ttk.Button(self.root, text="Load Image", command=self.load_image)
        self.load_image_button.grid(row=0, column=4)

        # Noise selection components
        self.noise_label = ttk.Label(self.root, text="Noise Type:")
        self.noise_label.grid(row=1, column=0, columnspan=2)
        self.noise_dropdown = ttk.Combobox(self.root, textvariable=self.noise_type, values=["Gaussian", "Salt & Pepper"])
        self.noise_dropdown.grid(row=1, column=1, columnspan=1)
        self.noise_dropdown.bind("<<ComboboxSelected>>", self.update_noise_components)

        self.mean_label = ttk.Label(self.root, text="Noise Mean:")
        self.mean_label.grid(row=2, column=0, columnspan=2)
        self.mean_entry = ttk.Entry(self.root)
        self.mean_entry.grid(row=2, column=1)

        self.variance_label = ttk.Label(self.root, text="Noise Variance:")
        self.variance_label.grid(row=2, column=2)
        self.variance_entry = ttk.Entry(self.root)
        self.variance_entry.grid(row=2, column=3)

        self.density_label = ttk.Label(self.root, text="Noise Density:")
        self.density_entry = ttk.Entry(self.root)   

        self.add_noise_button = ttk.Button(self.root, text="Add Noise", command=self.add_noise)
        self.add_noise_button.grid(row=2, column=4)

        # Filtering selection components
        self.filter_label = ttk.Label(self.root, text="Filter Type:")
        self.filter_label.grid(row=3, column=0, columnspan=2)
        self.filter_dropdown = ttk.Combobox(self.root, textvariable=self.filter_type, values=["Gaussian", "Median", "Averaging"])
        self.filter_dropdown.grid(row=3, column=1, columnspan=1)

        self.size_label = ttk.Label(self.root, text="Filter Size:")
        self.size_label.grid(row=4, column=0, columnspan=2)
        self.size_dropdown = ttk.Combobox(self.root, textvariable=self.filter_size, values=["3x3", "5x5", "7x7", "9x9", "11x11"])
        self.size_dropdown.grid(row=4, column=1, columnspan=1)

        self.apply_filter_button = ttk.Button(self.root, text="Apply Filter", command=self.apply_filter)
        self.apply_filter_button.grid(row=4, column=4)

        self.reset_button = ttk.Button(self.root, text="Reset", command=self.reset)
        self.reset_button.grid(row=5, column=0, columnspan=5)

        # Image display
        self.canvas = FigureCanvasTkAgg(self.fig, master=self.root)
        self.canvas_widget = self.canvas.get_tk_widget()
        self.canvas_widget.grid(row=6, column=0, columnspan=5)

    def load_image(self):
        image_path = self.image_name.get()
        self.original_image = io.imread(image_path, as_gray=True)  # Load as grayscale
        self.corrupted_image = self.original_image.copy()
        self.filtered_image = self.original_image.copy()
        self.display_images()

    def display_images(self):
        self.ax[0].cla()
        self.ax[1].cla()
        self.ax[2].cla()

        self.ax[0].imshow(self.original_image, cmap='gray')
        self.ax[0].set_title("Original Image")

        self.ax[1].imshow(self.corrupted_image, cmap='gray')
        self.ax[1].set_title("Corrupted Image")

        self.ax[2].imshow(self.filtered_image, cmap='gray')
        self.ax[2].set_title("Filtered Image")

        self.canvas.draw()

    def update_noise_components(self, event):
        if self.noise_type.get() == "Salt & Pepper":
            self.variance_label.grid_remove()
            self.variance_entry.grid_remove()
            self.mean_label.grid_remove()
            self.mean_entry.grid_remove()
            self.density_label.grid(row=2, column=2)
            self.density_entry.grid(row=2, column=3)
        else:
            self.density_label.grid_remove()
            self.density_entry.grid_remove()
            self.variance_label.grid(row=2, column=2)
            self.variance_entry.grid(row=2, column=3)
            self.mean_label.grid(row=2, column=0)
            self.mean_entry.grid(row=2, column=1)

    def add_noise(self):
        try:
            noise_type = self.noise_type.get()
            if noise_type == "Gaussian":
                mean = float(self.mean_entry.get())
                variance = float(self.variance_entry.get())
                noise = util.random_noise(np.array(self.original_image), mode='gaussian', mean=mean, var=variance)
            elif noise_type == "Salt & Pepper":
                density = float(self.density_entry.get())
                noise = util.random_noise(np.array(self.original_image), mode='s&p', amount=density)
        except ValueError:
            # Handle non-numerical input
            messagebox.showerror("Invalid Input", "Please enter numerical values for the noise mean, variance, or density.")
            return  # Exit the function without adding noise


        noise = (noise * 255).astype(np.uint8)
        self.corrupted_image = noise
        self.display_images()

    def apply_filter(self):
        filter_type = self.filter_type.get()
        size = int(self.filter_size.get().split('x')[0])
        kernel_size = (size, size)
        if filter_type == "Averaging":
            self.filtered_image = filters.rank.mean(np.array(self.corrupted_image), np.ones((size, size), dtype=int))
        elif filter_type == "Median":
            self.filtered_image = filters.rank.median(np.array(self.corrupted_image), np.ones((size, size), dtype=int))
        elif filter_type == "Gaussian":
            self.filtered_image = filters.gaussian(np.array(self.corrupted_image), sigma=size)

        self.filtered_image = self.filtered_image
        self.display_images()

    def reset(self):
        self.mean_entry.delete(0, tk.END)
        self.variance_entry.delete(0, tk.END)
        self.density_entry.delete(0, tk.END)
        self.noise_type.set("Gaussian")
        self.filter_type.set("Gaussian")
        self.filter_size.set("3x3")

        self.corrupted_image = self.original_image.copy()
        self.filtered_image = self.original_image.copy()

        self.display_images()

if __name__ == "__main__":
    root = tk.Tk()
    app = ImageProcessingApp(root)
    root.mainloop()
