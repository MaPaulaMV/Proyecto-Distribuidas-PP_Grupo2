#!/usr/bin/env python
# -*- coding: utf-8 -*-
#######################################################################
##                            LIBRERIAS                              ##
#######################################################################
from socket import *
import time
from _thread import *
import tkinter as tk
from tkinter import *
from tkinter import ttk
import socket

class Aplicacion():
    ''' Clase Aplicacion '''
    # Declara una variable de clase para contar ventanas
    ventana = 0
    # Declara una variable de clase para usar en el
    # cálculo de la posición de una ventana
    posx_y = 0
    exit=False      # Si el cliente envia salir, exit se pone en true y el
                # el programa termina
    client = ""
    #######################################################################
    ##                            FUNCIONES                              ##
    #######################################################################
    def marshallCompra(self,id_tran,tar,cvv,mes,ani,mon,iva,meses,vou):
        #print(tit+"|"+tar+"|"+mes+"|"+ani+"|"+cvv+"|"+mon+"|"+meses)
        m = float(mon)
        if iva=="0%":
            monto_iva = 0
            #print('COMPRA 0%.\n')
        elif iva=="12%":
            monto_iva = (m*12)/100
            #print('COMPRA 12%.\n')
        monto_total = m + monto_iva
        mi=round(monto_iva,2)
        mt=round(monto_total,2)           
        msgC_var = tk.StringVar()
        msgC_var = "CMP"+"|"+id_tran+"|"+tar+"|"+cvv+"|"+mes+"/"+ani+"|"+mon+"|"+str(mi)+"|"+str(mt)+"|"+meses+"|"+vou
        print(msgC_var)
        # Establecemos el tipo de socket/conexion
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        port = 1234 # Puerto de comunicacion
        # Realizamos la conexion al la IP y puerto
        #sock.connect(('25.76.226.113',port))
        sock.connect(('localhost',port))
        # Enviamos un mensaje
        sock.send(msgC_var.encode())
        # Leemos los datos del servidor
        data = sock.recv(4096)
        # Cerramos el socket
        sock.close()
        # Mostramos los datos recibidos
        #print(data.decode())
        resp = data.decode()
        print(resp)
        if resp.find("OK")>0:
            print('COMPRA OK.\n')
        else:
            print('ERROR DE INGRESO.\n')

    def marshallLogin(self, id, pin):
        msg_var = tk.StringVar()
        msg_var = "RGP|1723551057001|"+id+"|"+pin+"\n"
        print(msg_var) 
        # Establecemos el tipo de socket/conexion
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        port = 1234 # Puerto de comunicacion
        # Realizamos la conexion al la IP y puerto
        #sock.connect(('25.76.226.113',port))
        sock.connect(('localhost',port))
        # Enviamos un mensaje
        sock.send(msg_var.encode())
        # Leemos los datos del servidor
        data = sock.recv(4096)
        # Cerramos el socket
        sock.close()
        # Mostramos los datos recibidos
        #print(data.decode())
        resp = data.decode()
        print(resp)
        separador = "|"
        resp_sep = resp.split(separador)
        id_tran = resp_sep[0]
        print(resp_sep[0])
        if resp.find("OK")>0:
            self.menu(id_tran)
        else:
            print('ERROR DE INGRESO.\n')

    #######################################################################
    ##                              VISTAS                               ##
    #######################################################################
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
        id_var = tk.StringVar()
        cuadro_id = Entry(self.raiz, textvariable=id_var)
        cuadro_id.pack(side=TOP, padx=0, pady=0)
        pin_label = ttk.Label(self.raiz, text='PIN: ')
        pin_label.pack(side=TOP, padx=5, pady=5)
        pin_var = tk.StringVar()
        cuadro_pin = Entry(self.raiz, show ="*", textvariable=pin_var)
        cuadro_pin.pack(side=TOP, padx=0, pady=0)
        msg_var = tk.StringVar()
        boton = ttk.Button(self.raiz, text='ACCEDER', command=lambda: self.marshallLogin(id_var.get(),pin_var.get()))
        boton.pack(side=LEFT, padx=10, pady=10)
        boton = ttk.Button(self.raiz, text='SALIR', command=self.raiz.destroy)
        boton.pack(side=RIGHT, padx=10, pady=10)
        self.raiz.mainloop()

    def compra(self,id_tran):
        self.compra = Toplevel()
        self.compra.geometry('300x500+500+50')
        self.compra.resizable(0,0)
        ident = self.compra.winfo_id()
        # Construye mensaje de la barra de título
        titulo = "COMPRA #"+ str(ident)
        self.compra.title(titulo)
        login_label = ttk.Label(self.compra, text='COMPRA')
        login_label.pack(side=TOP, padx=5, pady=5)
        ###
        #-----------Seccion numero tarjeta----------------
        tarjeta_label= ttk.Label(self.compra, text="Número de la tarjeta:")
        tarjeta_label.pack(side=TOP, padx=0, pady=0)
        tarjeta_var = tk.StringVar()
        cuadro_tarjeta=Entry(self.compra, textvariable=tarjeta_var)
        cuadro_tarjeta.pack(side=TOP, padx=0, pady=0)
        #-----------Seccion cvv----------
        cvv_label= ttk.Label(self.compra, text="Código de seguridad (CVV):")
        cvv_label.pack(side=TOP, padx=5, pady=5)
        cvv_var = tk.StringVar()
        cuadro_cvv=Entry(self.compra, textvariable=cvv_var)
        cuadro_cvv.pack(side=TOP, padx=5, pady=5)
        #-----------Seccion fecha caducidad-------------
        fechaCad_label=ttk.Label(self.compra,text="FECHA CADUCIDAD")
        fechaCad_label.pack(side=TOP, padx=0, pady=0)
        mesCad_label=ttk.Label(self.compra,text="Mes:")
        mesCad_label.pack(side=TOP, padx=0, pady=0)
        mes_var = tk.StringVar()
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
                                    "12",], textvariable=mes_var)
        mesCombo.pack(side=TOP, padx=0, pady=0)
        mesCombo.current(0)
        anioCad_label=ttk.Label(self.compra,text="Anio:")
        anioCad_label.pack(side=TOP, padx=0, pady=0)
        anio_var = tk.StringVar()
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
                                    "30",], textvariable=anio_var)
        anioCombo.pack(side=TOP, padx=0, pady=0)
        anioCombo.current(0)
        #----------- Seccion monto---------
        monto_label= ttk.Label(self.compra, text="Monto:")
        monto_label.pack(side=TOP, padx=5, pady=5)
        monto_var = tk.StringVar()
        cuadro_monto=Entry(self.compra, textvariable=monto_var)
        cuadro_monto.pack(side=TOP, padx=5, pady=5)
        #----------- Seccion iva ----------
        iva_label=ttk.Label(self.compra,text="IVA:")
        iva_label.pack(side=TOP, padx=0, pady=0)
        iva_var = tk.StringVar()
        ivaCombo=ttk.Combobox(self.compra,
                            values=["0%",
                                    "12%"], textvariable=iva_var)
        ivaCombo.pack(side=TOP, padx=0, pady=0)
        ivaCombo.current(0)
        #-----------Seccion meses-------------
        monto_label= ttk.Label(self.compra, text="Meses:")
        monto_label.pack(side=TOP, padx=5, pady=5)
        meses_var = tk.StringVar()
        cuadro_meses= ttk.Entry(self.compra, textvariable=meses_var)
        cuadro_meses.pack(side=TOP, padx=5, pady=5)
        #----Seccion N# ReferenciaVoucher-----
        voucher_label = ttk.Label(self.compra, text="# Referencia Voucher")
        voucher_label.pack(side=TOP, padx=0, pady=0)
        voucher_var = tk.StringVar()
        cuadro_voucher = Entry(self.compra, textvariable=voucher_var)
        cuadro_voucher.pack(side=TOP, padx=0, pady=0)
        #-----------Boton Comprar-------------
        boton = tk.Button(self.compra, text='COMPRAR', 
                           command=lambda: self.marshallCompra(id_tran,tarjeta_var.get(),
                           cvv_var.get(),mes_var.get(),anio_var.get(),
                           monto_var.get(),iva_var.get(),meses_var.get(),voucher_var.get())) 
        boton.pack(side=LEFT, padx=10, pady=10)
        botonCancelar = ttk.Button(self.compra, text='CANCELAR', 
                           command=self.compra.destroy)   
        botonCancelar.pack(side=RIGHT, padx=10, pady=10)
        self.raiz.wait_window(self.compra)

    def menu(self,id_tran):
        ''' Construye una ventana MENU '''
        # Define una nueva ventana de diálogo
        self.menu = Toplevel()
        self.menu.geometry('300x210+500+50')
        # Incrementa en 1 el contador de ventanas
        #Aplicacion.ventana+=1
        # Recalcula posición de la ventana
        #Aplicacion.posx_y += 50
        #tamypos = '200x100+'+str(Aplicacion.posx_y)+ \
        #          '+'+ str(Aplicacion.posx_y)
        #self.dialogo.geometry(tamypos)
        self.menu.resizable(0,0)
        # Obtiene identicador de la nueva ventana 
        ident = self.menu.winfo_id()
        # Construye mensaje de la barra de título
        #titulo = str(Aplicacion.ventana)+": "+str(ident)
        titulo = "MENU POS #"+ str(ident)
        self.menu.title(titulo)
        # Define el botón 'Cerrar' que cuando sea
        # presionado cerrará (destruirá) la ventana 
        # 'self.dialogo' llamando al método
        # 'self.dialogo.destroy'
        login_label = ttk.Label(self.menu, text='MENU POS')
        login_label.pack(side=TOP, padx=5, pady=5)
        boton = ttk.Button(self.menu, text='COMPRAR', 
                           command=lambda: self.compra(id_tran))   
        boton.pack(side=TOP, padx=10, pady=10)
        botonCancelar = ttk.Button(self.menu, text='CANCER COMPRA', 
                           command=self.menu.destroy)   
        botonCancelar.pack(side=TOP, padx=10, pady=10)
        botonCancelar = ttk.Button(self.menu, text='SALIR', 
                           command=self.menu.destroy)   
        botonCancelar.pack(side=TOP, padx=10, pady=10)
        self.raiz.wait_window(self.menu)


def main():
    mi_app = Aplicacion()
    return(0)

if __name__ == '__main__':
    main()