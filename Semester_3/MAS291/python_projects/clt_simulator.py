import tkinter as tk
from tkinter import ttk
from tkinter.ttk import Style
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from scipy.stats import norm
import math

class CLTApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Mô phỏng Định lý Giới hạn Trung tâm (CLT)")
        self.root.geometry("1000x900")
        self.root.configure(bg='#f5f7fa')

        self.style = Style(self.root)
        self.style.theme_use('clam')
        self.config_styles() # Apply custom styles

        # --- Variables for simulation parameters ---
        self.mean_var = tk.IntVar(value=50) # Population mean (μ)
        self.std_var = tk.IntVar(value=10)  # Population standard deviation (σ)
        self.size_var = tk.IntVar(value=30) # Sample size (n)
        self.num_var = tk.IntVar(value=1000) # Number of samples
        self.simulating = tk.BooleanVar(value=False) # Flag to prevent multiple simulations

        self.rng_state = 0 # Seed for our custom random number generator

        # --- Setup main scrollable canvas ---
        self.main_cnv = tk.Canvas(self.root, bg='#f5f7fa', highlightthickness=0)
        self.scroll_y = ttk.Scrollbar(self.root, orient="vertical", command=self.main_cnv.yview)
        self.main_cnv.configure(yscrollcommand=self.scroll_y.set)
        
        self.scroll_y.pack(side="right", fill="y")
        self.main_cnv.pack(side="left", fill="both", expand=True, padx=20, pady=20)

        # --- Frame to hold all content inside the canvas ---
        self.content_frm = ttk.Frame(self.main_cnv, style='Page.TFrame')
        self.canvas_win_id = self.main_cnv.create_window((0, 0), window=self.content_frm, anchor="nw")

        # --- Create UI sections ---
        self.create_controls(self.content_frm) # Input sliders and button
        self.create_stats(self.content_frm)    # Statistics display boxes
        self.create_plot(self.content_frm)     # Matplotlib chart

        # --- Bind events ---
        self.content_frm.bind("<Configure>", self._on_frame_cfg) # Update scroll region
        self.main_cnv.bind("<Configure>", self._on_canvas_cfg)   # Resize content frame
        self.root.bind_all("<MouseWheel>", self._on_mouse_wheel) # Mouse wheel scrolling

        if hasattr(self, 'canvas'):
            # Bind mouse events for the plot
            self.canvas.mpl_connect("motion_notify_event", self.on_hover)
            self.canvas.mpl_connect("axes_leave_event", self.on_leave)

        # Run the first simulation on startup
        self.root.after(100, self.run_sim)

    # Custom pseudo-random number generator (linear congruential)
    def _seeded_random(self):
        self.rng_state = (self.rng_state * 9301 + 49297) % 233280
        return self.rng_state / 233280

    # Generate normal-distributed sample using Box-Muller transform
    def _gen_normal(self, mean, std, size):
        s = []
        for _ in range(size):
            u1 = self._seeded_random()
            u2 = self._seeded_random()

            if u1 == 0: u1 = 1e-10 # Avoid log(0)
            
            # Box-Muller formula
            z = math.sqrt(-2 * math.log(u1)) * math.cos(2 * math.pi * u2)
            s.append(mean + z * std) # Scale to desired mean and std
        return s

    # Update scroll region when content frame size changes
    def _on_frame_cfg(self, event):
        self.main_cnv.configure(scrollregion=self.main_cnv.bbox("all"))

    # Make content frame fill the canvas width
    def _on_canvas_cfg(self, event):
        self.main_cnv.itemconfig(self.canvas_win_id, width=event.width)
        
    # Handle mouse wheel scrolling
    def _on_mouse_wheel(self, event):
        val = -1 * (event.delta // 120)
        self.main_cnv.yview_scroll(val, "units")
    
    def _on_desc_wrap(self, event):
        self.lbl_desc.config(wraplength=event.width)

    # Define all custom widget styles
    def config_styles(self):
        BG = '#f5f7fa'
        CARD = '#ffffff'
        BLUE = '#2563eb'
        PURPLE = '#8b5cf6'
        TEXT = '#1e293b'
        SUBTEXT = '#64748b'

        self.style.configure('Page.TFrame', background=BG)
        self.style.configure('Card.TFrame', background=CARD, relief='flat')
        
        self.style.configure('Header.TLabel', 
                             background=CARD, 
                             foreground=TEXT, 
                             font=('Segoe UI', 24, 'bold'))
        self.style.configure('Subheader.TLabel', 
                             background=CARD, 
                             foreground=SUBTEXT, 
                             font=('Segoe UI', 11))
        
        self.style.configure('Slider.TLabel', 
                             background=CARD, 
                             foreground=TEXT, 
                             font=('Segoe UI', 10, 'bold'))

        self.style.configure('Primary.TButton', 
                             background=BLUE, 
                             foreground='white', 
                             font=('Segoe UI', 13, 'bold'), 
                             borderwidth=0,
                             padding=(20, 12))
        self.style.map('Primary.TButton',
                       background=[('active', '#1d4ed8'), ('disabled', '#94a3b8')])

        self.style.configure('Purple.Horizontal.TScale', 
                             troughcolor='#e2e8f0',
                             background=PURPLE,
                             borderwidth=0,
                             sliderlength=20,
                             sliderrelief='flat')

        self.style.configure('StatTitle.TLabel', 
                             background=CARD, 
                             foreground=SUBTEXT, 
                             font=('Segoe UI', 10))
        self.style.configure('StatValue.TLabel', 
                             background=CARD, 
                             font=('Segoe UI', 28, 'bold'))


    # Create the control panel with sliders
    def create_controls(self, parent):
        frm = ttk.Frame(parent, style='Card.TFrame', padding=30)
        frm.pack(fill=tk.X)
        frm.configure(relief='solid', borderwidth=1)
        frm.columnconfigure(0, weight=1)

        ttk.Label(frm, text="Central Limit Theorem", 
                  style='Header.TLabel').grid(row=0, column=0, sticky="w", pady=(0, 5))
        
        self.lbl_desc = ttk.Label(frm, text="The Central Limit Theorem says that the average of a large random sample is approximately normal, no matter the population's distribution.", 
                  style='Subheader.TLabel')
        self.lbl_desc.grid(row=1, column=0, sticky="ew", pady=(0, 30)) 
        self.lbl_desc.bind('<Configure>', self._on_desc_wrap)

        # --- Population Mean Slider ---
        self.lbl_mean = ttk.Label(frm, 
                                  text=f"Population Mean (μ): {self.mean_var.get():.0f}", 
                                  style='Slider.TLabel')
        self.lbl_mean.grid(row=2, column=0, sticky="w", pady=(0, 8))
        self.scl_mean = ttk.Scale(frm, from_=0, to=100, 
                                  orient=tk.HORIZONTAL, 
                                  variable=self.mean_var, 
                                  command=self.upd_slider_lbl, # Update label on change
                                  style='Purple.Horizontal.TScale')
        self.scl_mean.grid(row=3, column=0, sticky="ew", pady=(0, 20))

        # --- Population SD Slider ---
        self.lbl_std = ttk.Label(frm, 
                                 text=f"Population SD (σ): {self.std_var.get():.0f}", 
                                 style='Slider.TLabel')
        self.lbl_std.grid(row=4, column=0, sticky="w", pady=(0, 8))
        self.scl_std = ttk.Scale(frm, from_=1, to=30, 
                                 orient=tk.HORIZONTAL, 
                                 variable=self.std_var, 
                                 command=self.upd_slider_lbl, 
                                 style='Purple.Horizontal.TScale')
        self.scl_std.grid(row=5, column=0, sticky="ew", pady=(0, 20))

        # --- Sample Size Slider ---
        self.lbl_size = ttk.Label(frm, 
                                  text=f"Sample Size (n): {self.size_var.get()}", 
                                  style='Slider.TLabel')
        self.lbl_size.grid(row=6, column=0, sticky="w", pady=(0, 8))
        self.scl_size = ttk.Scale(frm, from_=5, to=100, 
                                  orient=tk.HORIZONTAL, 
                                  variable=self.size_var, 
                                  command=self.upd_slider_lbl, 
                                  style='Purple.Horizontal.TScale')
        self.scl_size.grid(row=7, column=0, sticky="ew", pady=(0, 20))

        # --- Number of Samples Slider ---
        self.lbl_num = ttk.Label(frm, 
                                 text=f"Number of Samples: {self.num_var.get()}", 
                                 style='Slider.TLabel')
        self.lbl_num.grid(row=8, column=0, sticky="w", pady=(0, 8))
        self.scl_num = ttk.Scale(frm, from_=100, to=5000, 
                                 orient=tk.HORIZONTAL, 
                                 variable=self.num_var, 
                                 command=self.upd_slider_lbl, 
                                 style='Purple.Horizontal.TScale')
        self.scl_num.grid(row=9, column=0, sticky="ew", pady=(0, 25))

        # --- Run Button ---
        self.btn_run = ttk.Button(frm, text="Run Simulation", 
                                  command=self.run_sim, # Call run_sim when clicked
                                  style='Primary.TButton')
        self.btn_run.grid(row=10, column=0, sticky="", pady=(10, 0))

    # Update slider labels when moved
    def upd_slider_lbl(self, event=None):
        self.lbl_mean.config(text=f"Population Mean (μ): {self.mean_var.get():.0f}")
        self.lbl_std.config(text=f"Population SD (σ): {self.std_var.get():.0f}")
        self.lbl_size.config(text=f"Sample Size (n): {self.size_var.get()}")
        self.lbl_num.config(text=f"Number of Samples: {self.num_var.get()}")

    # Create the 4 statistics display cards
    def create_stats(self, parent):
        cont = ttk.Frame(parent, style='Page.TFrame')
        cont.pack(fill=tk.X, pady=20)
        
        cont.columnconfigure((0, 1), weight=1, uniform='equal')
        cont.rowconfigure((0, 1), weight=1)

        self.stat_vars = {} # Dictionary to hold stat tk.StringVars
        info = [
            ("Population Mean (μ)", "#2563eb", 0, 0), 
            ("Sample Mean (X̄)", "#10b981", 0, 1),
            ("Theoretical SD (σ/√n)", "#8b5cf6", 1, 0), 
            ("Actual SD", "#f59e0b", 1, 1)
        ]

        for title, color, row, col in info:
            frm = ttk.Frame(cont, style='Card.TFrame', padding=20)
            frm.grid(row=row, column=col, sticky="nsew", padx=5, pady=5)
            frm.configure(relief='solid', borderwidth=1)
            
            ttk.Label(frm, text=title, style='StatTitle.TLabel').pack(pady=(0, 5))
            
            var = tk.StringVar(value="--")
            ttk.Label(frm, textvariable=var, style='StatValue.TLabel', 
                      foreground=color).pack()
            
            self.stat_vars[title] = var # Save var to update later

    # Create the Matplotlib plot area
    def create_plot(self, parent):
        frm = ttk.Frame(parent, style='Card.TFrame', padding=25)
        frm.pack(fill=tk.BOTH, expand=True)
        frm.configure(relief='solid', borderwidth=1)

        ttk.Label(frm, text="Distribution of Sample Means", 
                  style='Header.TLabel', 
                  font=('Segoe UI', 16, 'bold')).pack(pady=(0, 15))

        # --- Matplotlib Figure and Axis ---
        self.fig = plt.Figure(figsize=(9, 5), dpi=100, facecolor='#ffffff')
        self.ax = self.fig.add_subplot(111) # The plot area
        self.ax.set_facecolor('#ffffff')

        # --- Embed plot in Tkinter ---
        self.canvas = FigureCanvasTkAgg(self.fig, master=frm)
        self.canvas.get_tk_widget().pack(fill=tk.BOTH, expand=True)
        self.canvas.draw()
        
    # Start the simulation
    def run_sim(self):
        if self.simulating.get(): # Check if already running
            return
            
        self.simulating.set(True)
        self.btn_run.config(text="Simulating...", state=tk.DISABLED) # Disable button
        
        # Get current slider values
        p_mean = self.mean_var.get()
        p_std = self.std_var.get()
        s_size = self.size_var.get()
        n_samples = self.num_var.get()

        # Create a simple seed based on inputs
        seed = int(p_mean * 1000 + p_std * 100 + s_size * 10 + n_samples)
        self.rng_state = seed # Set the seed for our custom RNG
        
        # Run calculations after a short delay (to update UI)
        self.root.after(50, lambda: self.calc(p_mean, p_std, s_size, n_samples))

    # Perform the core CLT calculations
    def calc(self, p_mean, p_std, s_size, n_samples):
        # Calculate theoretical standard deviation (std error)
        th_std = p_std / np.sqrt(s_size)
        
        # --- Generate all sample means ---
        means_list = [np.mean(self._gen_normal(p_mean, p_std, s_size)) for _ in range(n_samples)]
        
        means = np.array(means_list)
        act_mean = np.mean(means) # Calculate mean of sample means
        act_std = np.std(means, ddof=1) # Calculate std dev of sample means

        # Update the UI with results
        self.upd_stats(p_mean, act_mean, th_std, act_std)
        self.upd_plot(means, p_mean, th_std)
        
        self.simulating.set(False)
        self.btn_run.config(text="Run Simulation", state=tk.NORMAL) # Re-enable button

    # Update the 4 statistics cards
    def upd_stats(self, p_mean, act_mean, th_std, act_std):
        self.stat_vars["Population Mean (μ)"].set(f"{p_mean:.3f}")
        self.stat_vars["Sample Mean (X̄)"].set(f"{act_mean:.3f}")
        self.stat_vars["Theoretical SD (σ/√n)"].set(f"{th_std:.3f}")
        self.stat_vars["Actual SD"].set(f"{act_std:.3f}")
        
    # Redraw the Matplotlib plot
    def upd_plot(self, means, p_mean, th_std):
        self.ax.clear() # Clear the old plot

        # Store plot params for hover event
        self.plot_mean = p_mean
        self.plot_std = th_std

        # --- Create histogram data ---
        counts, bins = np.histogram(means, bins=50, density=False)
        self.bin_mid = (bins[:-1] + bins[1:]) / 2 # X values (bin centers)
        self.counts_obs = counts # Y values (frequency)

        # --- Scaling factor for theoretical curve ---
        bin_w = bins[1] - bins[0]
        n_samples = len(means)
        self.scale_f = n_samples * bin_w
        
        # --- Plot observed data (histogram line) ---
        self.ax.plot(self.bin_mid, self.counts_obs, color='#2563eb', linewidth=2.5, 
                     label='Observed', alpha=0.9, marker='o', markersize=3)
        
        # --- Plot theoretical normal curve ---
        xmin, xmax = self.ax.get_xlim()
        self.x_th = np.linspace(xmin, xmax, 200) # X values for curve
        self.p_th = norm.pdf(self.x_th, loc=self.plot_mean, scale=self.plot_std) # Y values (PDF)
        
        self.ax.plot(self.x_th, self.p_th * self.scale_f, # Scale PDF to match histogram
                     linewidth=3, color='#ef4444', linestyle='--', 
                     label='Normal Distribution', alpha=0.9)
        
        # --- Format plot ---
        self.ax.set_xlabel("Sample Mean", fontsize=11, fontweight='bold')
        self.ax.set_ylabel("Frequency", fontsize=11, fontweight='bold')
        self.ax.grid(True, alpha=0.2, linestyle='--', linewidth=0.5)
        self.ax.spines['top'].set_visible(False)
        self.ax.spines['right'].set_visible(False)
        
        self.ax.legend(loc='upper center', bbox_to_anchor=(0.5, -0.15), 
                       frameon=False, shadow=False, fancybox=False, 
                       fontsize=10, ncol=2)
        
        self.fig.tight_layout(pad=3.0) 
        self.canvas.draw() # Redraw the canvas

        # --- Setup annotation box (for hover) ---
        self.annot = self.ax.annotate("", xy=(0,0), xytext=(20, -30),
            textcoords="offset points",
            bbox=dict(boxstyle="round,pad=0.5", fc="white", ec="#cccccc", lw=1))
        self.annot.set_visible(False)
        
        # --- Setup highlight points (for hover) ---
        self.hl_obs = self.ax.scatter([], [], s=50, c='#2563eb', zorder=10, ec='white', visible=False)
        self.hl_th = self.ax.scatter([], [], s=50, c='#ef4444', zorder=10, ec='white', visible=False)


    # Update annotation box content and position
    def upd_annot(self, event):
        x = event.xdata # Mouse x-position
        
        # Find nearest data point on the observed line
        idx = np.argmin(np.abs(self.bin_mid - x))
        x_obs = self.bin_mid[idx]
        y_obs = self.counts_obs[idx]
        
        if not hasattr(self, 'plot_mean') or not hasattr(self, 'plot_std'):
            return # Exit if plot is not ready

        # Calculate theoretical Y value at the same X
        y_th_pdf = norm.pdf(x_obs, 
                            loc=self.plot_mean, 
                            scale=self.plot_std)
        y_th = y_th_pdf * self.scale_f
    
        xlim = self.ax.get_xlim()
        ylim = self.ax.get_ylim()

        # Logic to position the annotation box nicely
        x_mid = (xlim[0] + xlim[1]) / 2
        x_off = -160 if event.xdata > x_mid else 40
        y_thresh = (ylim[1] - ylim[0]) * 0.75 + ylim[0]
        y_off = -40 if y_obs > y_thresh else 40
        
        self.annot.xyann = (x_off, y_off)
        self.annot.xy = (x_obs, y_obs)
        
        # Set annotation text
        text = f"Mean: {x_obs:.3f}\n" + \
               f"Observed : {y_obs:.0f}\n" + \
               f"Normal Distribution: {y_th:.4f}"
        
        self.annot.set_text(text)
        self.annot.set_visible(True)
        
        self.annot.set_zorder(11) 

        # Show highlight points
        self.hl_obs.set_offsets([x_obs, y_obs])
        self.hl_th.set_offsets([x_obs, y_th])
        
        self.hl_obs.set_visible(True)
        self.hl_th.set_visible(True)


    # Called when mouse moves over the plot
    def on_hover(self, event):
        if not hasattr(self, 'bin_mid') or \
           not hasattr(self, 'hl_obs') or \
           not hasattr(self, 'plot_mean'):
            return # Exit if data isn't ready

        vis = self.annot.get_visible()
        
        if event.inaxes == self.ax: # If mouse is inside the plot area
            self.upd_annot(event) # Update and show annotation
            self.canvas.draw_idle()
        elif vis: # If mouse left the plot area
            self.annot.set_visible(False) # Hide annotation
            self.hl_obs.set_visible(False)
            self.hl_th.set_visible(False)
            self.canvas.draw_idle()


    # Called when mouse leaves the plot
    def on_leave(self, event):
        self.annot.set_visible(False) # Hide annotation
        if hasattr(self, 'hl_obs'):
            self.hl_obs.set_visible(False)
        if hasattr(self, 'hl_th'):
            self.hl_th.set_visible(False)
        self.canvas.draw_idle()

# --- Main entry point ---
if __name__ == "__main__":
    root = tk.Tk() # Create the main window
    app = CLTApp(root) # Create our application instance
    root.mainloop() # Start the Tkinter event loop