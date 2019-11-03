#!/usr/bin/env python
# -*- coding: utf-8 -*-

from tkinter import *
from tkinter import ttk

class Aplicacion():
    ''' Clase Aplicacion '''
    
    # Declara una variable de clase para contar ventanas
    
    ventana = 0

    # Declara una variable de clase para usar en el
    # cálculo de la posición de una ventana
    
    posx_y = 0
        
    def __init__(self):
        ''' Construye ventana de POS '''              
        # Declara ventana de aplicación
        self.raiz = Tk()
        # Define dimensión de la ventana 300x210
        # que se situará en la coordenada x=500,y=50 
        self.raiz.geometry('300x210+500+50')
        # la define como estática, sin redimensionamiento 
        self.raiz.resizable(0,0)
        self.raiz.title("POS")
        self.raiz.iconbitmap("shop.ico")
        login_label = ttk.Label(self.raiz, text='LOGIN')
        login_label.pack(side=TOP, padx=5, pady=5)
        ruc_label = ttk.Label(self.raiz, text='RUC: 1234567890009')
        ruc_label.pack(side=TOP, padx=5, pady=5)
        id_label = ttk.Label(self.raiz, text='ID POST:')
        id_label.pack(side=TOP, padx=5, pady=5)
        cuadro_id = Entry(self.raiz)
        cuadro_id.pack(side=TOP, padx=0, pady=0)
        pin_label = ttk.Label(self.raiz, text='PIN: ')
        pin_label.pack(side=TOP, padx=5, pady=5)
        cuadro_pin = Entry(self.raiz)
        cuadro_pin.pack(side=TOP, padx=0, pady=0)
        boton = ttk.Button(self.raiz, text='ACCEDER', command=self.menu)
        boton.pack(side=LEFT, padx=10, pady=10)
        boton = ttk.Button(self.raiz, text='SALIR', command=self.raiz.destroy)
        boton.pack(side=RIGHT, padx=10, pady=10)
        self.raiz.mainloop()
        
    def compra(self):
        self.compra = Toplevel()
        self.compra.geometry('300x450+500+50')
        self.compra.resizable(0,0)
        ident = self.compra.winfo_id()
        # Construye mensaje de la barra de título
        titulo = "COMPRA #"+ str(ident)
        self.compra.title(titulo)
        login_label = ttk.Label(self.compra, text='COMPRA')
        login_label.pack(side=TOP, padx=5, pady=5)
        ###
        #--------Seccion nombre titular--------------
        titular_label = ttk.Label(self.compra, text="Titular de la tarjeta")
        titular_label.pack(side=TOP, padx=0, pady=0)
        cuadro_titular = Entry(self.compra)
        cuadro_titular.pack(side=TOP, padx=0, pady=0)

        #-----------Seccion numero tarjeta----------------
        tarjeta_label= ttk.Label(self.compra, text="Número de la tarjeta:")
        tarjeta_label.pack(side=TOP, padx=0, pady=0)
        cuadro_tarjeta=Entry(self.compra)
        cuadro_tarjeta.pack(side=TOP, padx=0, pady=0)

        #-----------Seccion fecha caducidad-------------
        fechaCad_label=ttk.Label(self.compra,text="FECHA CADUCIDAD")
        fechaCad_label.pack(side=TOP, padx=0, pady=0)
        mesCad_label=ttk.Label(self.compra,text="Mes:")
        mesCad_label.pack(side=TOP, padx=0, pady=0)
        mesCombo=ttk.Combobox(self.compra,
                            values=["01",
                                    "02",
                                    "03",
                                    "04",
                                    "05",
                                    "06",
                                    "07",
                                    "08",
                                    "09",
                                    "10",
                                    "11",
                                    "12",])

        mesCombo.pack(side=TOP, padx=0, pady=0)
        mesCombo.current(0)

        anioCad_label=ttk.Label(self.compra,text="Anio:")
        anioCad_label.pack(side=TOP, padx=0, pady=0)
        anioCombo=ttk.Combobox(self.compra,
                            values=["19",
                                    "20",
                                    "21",
                                    "22",
                                    "23",
                                    "24",
                                    "25",
                                    "26",
                                    "27",
                                    "28",
                                    "29",
                                    "30",])
        anioCombo.pack(side=TOP, padx=0, pady=0)
        anioCombo.current(0)


        #-----------Seccion cvv
        cvv_label= ttk.Label(self.compra, text="Código de seguridad (CVV):")
        cvv_label.pack(side=TOP, padx=5, pady=5)
        cuadro_cvv=Entry(self.compra)
        cuadro_cvv.pack(side=TOP, padx=5, pady=5)

        #----------- Seccion monto---------
        monto_label= ttk.Label(self.compra, text="Monto:")
        monto_label.pack(side=TOP, padx=5, pady=5)
        cuadro_monto=Entry(self.compra)
        cuadro_monto.pack(side=TOP, padx=5, pady=5)

        #-----------Seccion meses-------------
        monto_label= ttk.Label(self.compra, text="Meses:")
        monto_label.pack(side=TOP, padx=5, pady=5)
        cuadro_meses=Entry(self.compra)
        cuadro_meses.pack(side=TOP, padx=5, pady=5)

        ###
        boton = ttk.Button(self.compra, text='COMPRAR', 
                           command=self.compra.destroy)   
        boton.pack(side=LEFT, padx=10, pady=10)
        botonCancelar = ttk.Button(self.compra, text='CANCELAR', 
                           command=self.compra.destroy)   
        botonCancelar.pack(side=RIGHT, padx=10, pady=10)
        self.raiz.wait_window(self.compra)

    def menu(self):
        ''' Construye una ventana MENU '''
        # Define una nueva ventana de diálogo
        self.dialogo = Toplevel()
        self.dialogo.geometry('300x210+500+50')
        # Incrementa en 1 el contador de ventanas
        #Aplicacion.ventana+=1
        # Recalcula posición de la ventana
        #Aplicacion.posx_y += 50
        #tamypos = '200x100+'+str(Aplicacion.posx_y)+ \
        #          '+'+ str(Aplicacion.posx_y)
        #self.dialogo.geometry(tamypos)
        self.dialogo.resizable(0,0)
        # Obtiene identicador de la nueva ventana 
        ident = self.dialogo.winfo_id()
        # Construye mensaje de la barra de título
        
        #titulo = str(Aplicacion.ventana)+": "+str(ident)
        titulo = "MENU POS #"+ str(ident)
        self.dialogo.title(titulo)
        
        # Define el botón 'Cerrar' que cuando sea
        # presionado cerrará (destruirá) la ventana 
        # 'self.dialogo' llamando al método
        # 'self.dialogo.destroy'
        login_label = ttk.Label(self.dialogo, text='MENU POS')
        login_label.pack(side=TOP, padx=5, pady=5)
        boton = ttk.Button(self.dialogo, text='COMPRAR', 
                           command=self.compra)   
        boton.pack(side=TOP, padx=10, pady=10)
        botonCancelar = ttk.Button(self.dialogo, text='CANCER COMPRA', 
                           command=self.dialogo.destroy)   
        botonCancelar.pack(side=TOP, padx=10, pady=10)
        botonCancelar = ttk.Button(self.dialogo, text='SALIR', 
                           command=self.dialogo.destroy)   
        botonCancelar.pack(side=TOP, padx=10, pady=10)
        # Cuando la ejecución del programa llega a este 
        # punto se utiliza el método wait_window() para
        # esperar que la ventana 'self.dialogo' sea 
        # destruida. 
        # Mientras tanto se atiende a los eventos locales
        # que se produzcan, por lo que otras partes de la
        # aplicación seguirán funcionando con normalidad. 
        # Si hay código después de esta línea se ejecutará
        # cuando la ventana 'self.dialogo' sea cerrada.
        self.raiz.wait_window(self.dialogo)

def main():
    mi_app = Aplicacion()
    return(0)

if __name__ == '__main__':
    main()