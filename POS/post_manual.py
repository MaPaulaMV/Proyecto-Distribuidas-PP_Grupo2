from tkinter import *
from tkinter import ttk

from socket import *
import time
from _thread import *

exit=False

host="192.168.5.1"
port=666

RUC="1837465345001"

class Aplicacion():
    
    ventana = 0    
    posx_y = 0

    #-----------------------socket-----------------------
    
    def crearSocket(self):
        s = socket(AF_INET, SOCK_STREAM)
        return s

    def conectarse (self,host, port, s):
        s.connect((host, port))

    def intentoConexion(self,host, port, s):

        while True:
            print("\nTrying to connect to:", host + ":" + str(port))
            try:
                conectarse(host, port, s)
                break
            except:
                print("There is no Server at:", host + ":" + str(port))
                print("Trying again in 5 Seconds\n")
                time.sleep(5)

    def enviar(self,s,mensaje):

        while True:

            global exit

            try:
                msg = mensaje + "\n"
                print(msg)
                s.send(msg.encode("UTF-8"))
                start_new_thread(recibir,(s,))
                exit=True
                s.close
                return


            except:
                print("Something happend\n")
                print("Trying in 5 seg")
                time.sleep(5)

    def recibir(self,s):
        while True:
            try:
                reply = s.recv(2048)
                print(reply.decode("UTF-8"))
                break


            except:
                print("Cant recieve response\n")
                print("Trying in 5 seg")
                time.sleep(5)

    def recibirEspecial(self,s):
        global client
        client = s.recv(2048).decode("UTF-8")

    #ventanas
    def __init__(self):

        self.raiz = Tk()
        
        self.raiz.geometry('300x210+500+50')
        
        self.raiz.resizable(0,0)
        self.raiz.title("POST MANUAL")
        self.raiz.iconbitmap("espe.ico")

        login_label = ttk.Label(self.raiz, text='INGRESO')
        login_label.pack(side=TOP, padx=5, pady=5)
        ruc_label = ttk.Label(self.raiz, text='RUC: ' + RUC)
        ruc_label.pack(side=TOP, padx=5, pady=5)
        id_label = ttk.Label(self.raiz, text='ID POST:')
        id_label.pack(side=TOP, padx=5, pady=5)
        cuadro_id = Entry(self.raiz)
        cuadro_id.pack(side=TOP, padx=0, pady=0)
        pin_label = ttk.Label(self.raiz, text='PIN: ')
        pin_label.pack(side=TOP, padx=5, pady=5)
        cuadro_pin = Entry(self.raiz)
        cuadro_pin.pack(side=TOP, padx=0, pady=0)
        
        boton_acceder = ttk.Button(self.raiz, text='ACCEDER', command=lambda: self.enviarDatos("REG",RUC,cuadro_id.get(),cuadro_pin.get()))
        boton_acceder.pack(side=LEFT, padx=10, pady=10)
        boton_salir = ttk.Button(self.raiz, text='SALIR', command=self.raiz.destroy)
        boton_salir.pack(side=RIGHT, padx=10, pady=10)
        
        self.raiz.mainloop()

        
    def enviarDatos(self,idTrans,ruc,id,pin):
        s=lambda: self.crearSocket()
        lambda: self.intentoConexion(host,port,s)
        print("\nConnection To Server Established!\nThe server is:", host+":"+str(port)+"\n")

        mensaje=idTrans+"|"+ruc+"|"+id+"|"+pin
        print("Su mensaje para enviar es:" + mensaje)
        lambda: self.enviar,(s,)

        print("\nSorry something went wrong! You have lost connection to the server.:(")
        print("Closing the windows in 5 seg")
        time.sleep(10)

        if id=='1':
            self.menu()
            print("pasar a la otra")

    def menu(self):
        
        self.dialogo = Toplevel()
        self.dialogo.geometry('300x210+500+50')
        self.dialogo.resizable(0,0)
        
        self.dialogo.title("Menu Opciones")
        login_label = ttk.Label(self.dialogo, text='MENU POS')
        login_label.pack(side=TOP, padx=5, pady=5)
        botonComprar = ttk.Button(self.dialogo, text='COMPRAR', 
                           command=self.dialogo.destroy)   
        botonComprar.pack(side=TOP, padx=10, pady=10)
        botonCancelar = ttk.Button(self.dialogo, text='CANCER COMPRA', 
                           command=self.dialogo.destroy)   
        botonCancelar.pack(side=TOP, padx=10, pady=10)
        botonCancelar = ttk.Button(self.dialogo, text='SALIR', 
                           command=self.dialogo.destroy)   
        botonCancelar.pack(side=TOP, padx=10, pady=10)
        self.raiz.wait_window(self.dialogo)

    def VenCancelar(self):
        self.cancelar = Toplevel()
        self.cancelar.geometry('300x210+500+50')
        self.cancelar.resizable(0,0)
        
        self.cancelar.title("Cancelacion")
        login_label = ttk.Label(self.raiz, text='CANCELAR TRANSACCION')
        login_label.pack(side=TOP, padx=5, pady=5)
        ruc_label = ttk.Label(self.raiz, text='RUC: 1734543313001')
        ruc_label.pack(side=TOP, padx=5, pady=5)
        tarjeta_label=ttk.Label(self, text='Tarjeta: ')
        cuadro_tarjeta = Entry(self.raiz)
        cuadro_tarjeta.pack(side=TOP, padx=0, pady=0)
        cvv_label = ttk.Label(self.raiz, text='CVV: ')
        cvv_label.pack(side=TOP, padx=5, pady=5)
        cuadro_cvv = Entry(self.raiz)
        cuadro_cvv.pack(side=TOP, padx=0, pady=0)
        fechaExp_label = ttk.Label(self.raiz, text='Fecha Expiracion: ')
        fechaExp_label.pack(side=TOP, padx=5, pady=5)
        cuadro_fechaExp = Entry(self.raiz)
        cuadro_fechaExp.pack(side=TOP, padx=0, pady=0)
        voucher_label = ttk.Label(self.raiz, text='Numero de Voucher: ')
        voucher_label.pack(side=TOP, padx=5, pady=5)
        cuadro_voucher = Entry(self.raiz)
        cuadro_voucher.pack(side=TOP, padx=0, pady=0)


def main():
    mi_app = Aplicacion()
    return(0)

if __name__ == '__main__':
    main()
