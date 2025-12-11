import tkinter as tk
from tkinter import ttk
import random

class MontyHallGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Monty Hall Problem")
        self.root.geometry("800x700")
        self.root.configure(bg='#1a0b3d')
        
        self.COLOR_BG = '#1a0b3d'
        self.COLOR_DOOR = '#d97706'
        self.COLOR_DOOR_SELECTED = '#fbbf24'
        self.COLOR_WIN = '#22c55e'
        self.COLOR_LOSE = '#ef4444'
        self.COLOR_TEXT = 'white'
        self.COLOR_TEXT_ACCENT = '#a0c4ff'
        self.COLOR_FRAME = '#2d1f4a'
        
        self.num_doors = 3
        self.game_state = 'initial'  # initial, select, switch, reveal
        self.car_door = None
        self.selected_door = None
        self.opened_door = None
        self.stats = {
            'stay_wins': 0, 'stay_losses': 0,
            'switch_wins': 0, 'switch_losses': 0
        }
        
        self.create_widgets()
    
    def create_widgets(self):
        # Title
        title_frame = tk.Frame(self.root, bg=self.COLOR_BG)
        title_frame.pack(pady=20)
        
        tk.Label(
            title_frame,
            text="Monty Hall Problem",
            font=('Arial', 28, 'bold'),
            fg=self.COLOR_TEXT,
            bg=self.COLOR_BG
        ).pack()
        
        tk.Label(
            title_frame,
            text="Pick a door, Monty opens 1 empty door, then decide: stay or switch?",
            font=('Arial', 11),
            fg=self.COLOR_TEXT_ACCENT,
            bg=self.COLOR_BG
        ).pack()
        
        # Door count control
        self.door_control_frame = tk.Frame(self.root, bg=self.COLOR_BG)
        self.door_control_frame.pack(pady=15)
        
        self.minus_btn = tk.Button(
            self.door_control_frame,
            text="-",
            font=('Arial', 16, 'bold'),
            bg='#4a3a6a',
            fg=self.COLOR_TEXT,
            width=3,
            command=lambda: self.change_doors(-1)
        )
        self.minus_btn.pack(side=tk.LEFT, padx=5)
        
        self.door_label = tk.Label(
            self.door_control_frame,
            text=f"{self.num_doors} Doors",
            font=('Arial', 14, 'bold'),
            fg=self.COLOR_TEXT,
            bg=self.COLOR_BG
        )
        self.door_label.pack(side=tk.LEFT, padx=10)
        
        self.plus_btn = tk.Button(
            self.door_control_frame,
            text="+",
            font=('Arial', 16, 'bold'),
            bg='#4a3a6a',
            fg=self.COLOR_TEXT,
            width=3,
            command=lambda: self.change_doors(1)
        )
        self.plus_btn.pack(side=tk.LEFT, padx=5)
        
        # Stats box
        stats_frame = tk.Frame(self.root, bg=self.COLOR_FRAME, highlightbackground='#5a4a7a', highlightthickness=2)
        stats_frame.pack(pady=10, padx=50, fill=tk.X)
        
        stats_inner = tk.Frame(stats_frame, bg=self.COLOR_FRAME)
        stats_inner.pack(pady=15, padx=20)
        
        # Stay stats
        stay_frame = tk.Frame(stats_inner, bg=self.COLOR_FRAME)
        stay_frame.grid(row=0, column=0, padx=30)
        
        tk.Label(
            stay_frame,
            text="Stay Strategy",
            font=('Arial', 10),
            fg=self.COLOR_TEXT_ACCENT,
            bg=self.COLOR_FRAME
        ).pack()
        
        self.stay_rate_label = tk.Label(
            stay_frame,
            text="0%",
            font=('Arial', 24, 'bold'),
            fg=self.COLOR_TEXT,
            bg=self.COLOR_FRAME
        )
        self.stay_rate_label.pack()
        
        self.stay_record_label = tk.Label(
            stay_frame,
            text="0W / 0L",
            font=('Arial', 9),
            fg='#8ab4f8',
            bg=self.COLOR_FRAME
        )
        self.stay_record_label.pack()
        
        # Switch stats
        switch_frame = tk.Frame(stats_inner, bg=self.COLOR_FRAME)
        switch_frame.grid(row=0, column=1, padx=30)
        
        tk.Label(
            switch_frame,
            text="Switch Strategy",
            font=('Arial', 10),
            fg=self.COLOR_TEXT_ACCENT,
            bg=self.COLOR_FRAME
        ).pack()
        
        self.switch_rate_label = tk.Label(
            switch_frame,
            text="0%",
            font=('Arial', 24, 'bold'),
            fg=self.COLOR_TEXT,
            bg=self.COLOR_FRAME
        )
        self.switch_rate_label.pack()
        
        self.switch_record_label = tk.Label(
            switch_frame,
            text="0W / 0L",
            font=('Arial', 9),
            fg='#8ab4f8',
            bg=self.COLOR_FRAME
        )
        self.switch_record_label.pack()
        
        # Theory label
        self.theory_label = tk.Label(
            stats_frame,
            text="",
            font=('Arial', 9),
            fg=self.COLOR_TEXT_ACCENT,
            bg=self.COLOR_FRAME
        )
        self.theory_label.pack(pady=(10, 15))
        
        # Doors frame
        self.doors_frame = tk.Frame(self.root, bg=self.COLOR_BG)
        self.doors_frame.pack(pady=20)
        
        self.door_buttons = []
        self.door_labels = []
        self.create_doors()
        
        # Message label
        self.message_label = tk.Label(
            self.root,
            text="",
            font=('Arial', 14),
            fg=self.COLOR_TEXT,
            bg=self.COLOR_BG
        )
        self.message_label.pack(pady=10)
        
        # Control buttons
        self.control_frame = tk.Frame(self.root, bg=self.COLOR_BG)
        self.control_frame.pack(pady=20)
        
        self.start_btn = tk.Button(
            self.control_frame,
            text="Start Game",
            font=('Arial', 14, 'bold'),
            bg=self.COLOR_WIN,
            fg=self.COLOR_TEXT,
            width=12,
            height=2,
            command=self.start_game
        )
        
        self.stay_btn = tk.Button(
            self.control_frame,
            text="Stay",
            font=('Arial', 14, 'bold'),
            bg='#3b82f6',
            fg=self.COLOR_TEXT,
            width=10,
            height=2,
            command=lambda: self.make_choice(False)
        )
        
        self.switch_btn = tk.Button(
            self.control_frame,
            text="Switch",
            font=('Arial', 14, 'bold'),
            bg='#a855f7',
            fg=self.COLOR_TEXT,
            width=10,
            height=2,
            command=lambda: self.make_choice(True)
        )
        
        self.play_again_btn = tk.Button(
            self.control_frame,
            text="Play Again",
            font=('Arial', 14, 'bold'),
            bg=self.COLOR_WIN,
            fg=self.COLOR_TEXT,
            width=12,
            height=2,
            command=self.start_game
        )
        
        self.update_display()
    
    def create_doors(self):
        for widget in self.doors_frame.winfo_children():
            widget.destroy()
        
        self.door_buttons = []
        self.door_labels = []
        
        for i in range(self.num_doors):
            door_container = tk.Frame(self.doors_frame, bg=self.COLOR_BG)
            door_container.pack(side=tk.LEFT, padx=10)
            
            btn = tk.Button(
                door_container,
                text=str(i + 1),
                font=('Arial', 36, 'bold'),
                bg=self.COLOR_DOOR,
                fg=self.COLOR_TEXT,
                width=4,
                height=3, # Thay đổi height để cân đối hơn
                command=lambda d=i: self.select_door(d),
                relief=tk.FLAT, # Thêm để loại bỏ viền
                activebackground=self.COLOR_DOOR_SELECTED # Thêm màu khi nhấn
            )
            btn.pack()
            
            label = tk.Label(
                door_container,
                text="",
                font=('Arial', 10),
                fg=self.COLOR_TEXT,
                bg=self.COLOR_BG
            )
            label.pack(pady=5)
            
            self.door_buttons.append(btn)
            self.door_labels.append(label)
    
    def change_doors(self, delta):
        ## FIX: Chỉ cho phép thay đổi nếu ở trạng thái 'initial' hoặc 'reveal'
        if self.game_state not in ['initial', 'reveal']:
            return
        
        new_num = max(3, min(5, self.num_doors + delta))
        if new_num != self.num_doors:
            self.num_doors = new_num
            self.stats = {'stay_wins': 0, 'stay_losses': 0, 'switch_wins': 0, 'switch_losses': 0}
            self.create_doors()
            ## FIX: Đảm bảo reset về trạng thái 'initial' khi đổi cửa
            self.game_state = 'initial'
            self.update_display()
    
    def start_game(self):
        self.car_door = random.randint(0, self.num_doors - 1)
        self.selected_door = None
        self.opened_door = None
        self.game_state = 'select'
        self.update_display()
    
    def select_door(self, door):
        if self.game_state != 'select':
            return
        
        self.selected_door = door
        
        # Monty opens one door
        available = [d for d in range(self.num_doors)
                     if d != door and d != self.car_door]
        self.opened_door = random.choice(available)
        
        self.game_state = 'switch'
        self.update_display()
    
    def make_choice(self, switch_choice):
        if switch_choice:
            ## FIX: Logic tìm cửa switch đúng
            # Tìm cửa duy nhất không phải cửa đã chọn và không phải cửa Monty mở
            final_door = next(d for d in range(self.num_doors)
                              if d != self.selected_door and d != self.opened_door)
        else:
            final_door = self.selected_door
        
        won = (final_door == self.car_door)
        
        # Update stats
        if switch_choice:
            if won:
                self.stats['switch_wins'] += 1
            else:
                self.stats['switch_losses'] += 1
        else:
            if won:
                self.stats['stay_wins'] += 1
            else:
                self.stats['stay_losses'] += 1
        
        self.selected_door = final_door
        self.game_state = 'reveal'
        self.update_display()
    
    def update_display(self):
        # Update stats
        total_stay = self.stats['stay_wins'] + self.stats['stay_losses']
        total_switch = self.stats['switch_wins'] + self.stats['switch_losses']
        stay_rate = (self.stats['stay_wins'] / total_stay * 100) if total_stay > 0 else 0
        switch_rate = (self.stats['switch_wins'] / total_switch * 100) if total_switch > 0 else 0
        
        self.stay_rate_label.config(text=f"{stay_rate:.1f}%")
        self.stay_record_label.config(text=f"{self.stats['stay_wins']}W / {self.stats['stay_losses']}L")
        self.switch_rate_label.config(text=f"{switch_rate:.1f}%")
        self.switch_record_label.config(text=f"{self.stats['switch_wins']}W / {self.stats['switch_losses']}L")
        
        # Update theory
        if self.game_state in ['switch', 'reveal']:
            theory_stay = 100 / self.num_doors
            ## FIX: Logic tính toán lý thuyết "Switch" đã được sửa
            theory_switch = (self.num_doors - 1) / self.num_doors * 100
            self.theory_label.config(text=f"Theory ({self.num_doors} doors): Stay ≈ {theory_stay:.1f}% | Switch ≈ {theory_switch:.1f}%")
        else:
            self.theory_label.config(text="")
        
        # Update doors
        for i, (btn, label) in enumerate(zip(self.door_buttons, self.door_labels)):
            is_selected = self.selected_door == i
            is_opened = self.opened_door == i
            show_content = self.game_state == 'reveal' or is_opened
            has_car = i == self.car_door
            
            # Mặc định reset màu
            btn_bg = self.COLOR_DOOR
            
            if show_content:
                if has_car:
                    btn.config(text="🏆", bg=self.COLOR_WIN)
                else:
                    btn.config(text="✗", bg=self.COLOR_LOSE)
            else:
                btn.config(text=str(i + 1), bg=self.COLOR_DOOR)
            
            ## FIX: Thay đổi logic highlight bằng màu nền
            if is_selected and self.game_state != 'reveal':
                btn.config(bg=self.COLOR_DOOR_SELECTED) # Highlight cửa đã chọn
            
            if is_opened:
                label.config(text="Opened")
            elif is_selected and self.game_state != 'reveal':
                label.config(text="Selected")
            else:
                label.config(text="")
                
            ## FIX: Vô hiệu hóa các nút cửa khi không ở trạng thái 'select'
            if self.game_state == 'select':
                btn.config(state=tk.NORMAL)
            else:
                btn.config(state=tk.DISABLED)

        # Update door control visibility & state
        ## FIX: Ẩn/hiện và vô hiệu hóa nút điều khiển cửa chính xác
        if self.game_state in ['initial', 'reveal']:
            self.door_control_frame.pack(pady=15)
            self.minus_btn.config(state=tk.NORMAL if self.num_doors > 3 else tk.DISABLED)
            self.plus_btn.config(state=tk.NORMAL if self.num_doors < 5 else tk.DISABLED)
        else:
            # Ẩn đi khi game đang diễn ra
            self.door_control_frame.pack_forget() 
        
        # Update message and buttons
        for widget in self.control_frame.winfo_children():
            widget.pack_forget()
        
        if self.game_state == 'initial':
            self.message_label.config(text="")
            self.start_btn.pack()
        elif self.game_state == 'select':
            self.message_label.config(text="Choose a door!")
        elif self.game_state == 'switch':
            self.message_label.config(text=f"Door {self.opened_door + 1} is empty. Switch your choice?")
            self.stay_btn.pack(side=tk.LEFT, padx=10)
            self.switch_btn.pack(side=tk.LEFT, padx=10)
        elif self.game_state == 'reveal':
            if self.selected_door == self.car_door:
                self.message_label.config(text="You Won!")
            else:
                self.message_label.config(text="You Lost!")
            self.play_again_btn.pack()
        
        self.door_label.config(text=f"{self.num_doors} Doors")


if __name__ == "__main__":
    root = tk.Tk()
    app = MontyHallGUI(root)
    root.mainloop()