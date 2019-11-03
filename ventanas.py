
import tkinter as tk
from tkinter import ttk
from tkinter import *
from tkinter import messagebox

class Cancelacion(ttk.Frame):
    
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)


        login_label = ttk.Label(self, text='CANCELACION')
        login_label.pack(side=TOP, padx=5, pady=5)
        ruc_label = ttk.Label(self, text='RUC: 1234567890009')
        ruc_label.pack(side=TOP, padx=5, pady=5)
        id_label = ttk.Label(self, text='ID TRANSACCION:')
        id_label.pack(side=TOP, padx=5, pady=5)
        trnas_id = Entry(self)

        trnas_id.pack(side=TOP, padx=0, pady=0)
        pin_label = ttk.Label(self, text='PIN: ')
        pin_label.pack(side=TOP, padx=5, pady=5)
        pin_id = Entry(self)
        pin_id.pack(side=TOP, padx=0, pady=0)


        pin_label = ttk.Label(self, text='CVV: ')
        pin_label.pack(side=TOP, padx=5, pady=5)
        cvv_id = Entry(self)
        cvv_id.pack(side=TOP, padx=0, pady=0)

        pin_label = ttk.Label(self, text='Tarjeta: ')
        pin_label.pack(side=TOP, padx=5, pady=5)    
        tar_id = Entry(self)
        tar_id.pack(side=TOP, padx=0, pady=0)

    
        pin_label = ttk.Label(self, text='Fecha Expiracion: ')
        pin_label.pack(side=TOP, padx=5, pady=5)     
        fexp_id = Entry(self)
        fexp_id.pack(side=TOP, padx=0, pady=0)
        

        boton = ttk.Button(self, text='CANCELAR TRANSACCION')
        boton.pack(side=LEFT, padx=10, pady=10)
        boton = ttk.Button(self, text='ACEPTAR', command= lambda: self.confirmation(trnas_id.get(),tar_id.get() ))
        boton.pack(side=RIGHT, padx=10, pady=10)
        
    def confirmation(self,vou,tarjeta):
     cadena="Eliminar: "+ vou+"\n"+"de la tarjeta: "+tarjeta
     messagebox.showinfo("Confirmacion de cancelacion", cadena)
        #self.greet_button = ttk.Button(
        #    self, text="Saludar", command=self.say_hello)
        #self.greet_button.pack()
        
        #self.greet_label = ttk.Label(self)
        #self.greet_label.pack()
    
    #def say_hello(self):
       # self.greet_label["text"] = \
           #3 "¡Hola, {}!".format(self.name_entry.get())


        

class Application(ttk.Frame):
    
    def __init__(self, main_window):
        super().__init__(main_window)
        main_window.title("POS-VENTAS")
        
        self.notebook = ttk.Notebook(self)
        
        self.greeting_frame = Cancelacion(self.notebook)
        self.notebook.add(
            self.greeting_frame, text="Cancelacion", padding=10)
        
  
        #self.log_frame=VentanaK(self.notebook)
        #self.notebook.add(self.log_frame, text="LOGIN", padding=10)

        self.notebook.pack(padx=10, pady=10)
        self.pack()




main_window = tk.Tk()
app = Application(main_window)
app.mainloop()