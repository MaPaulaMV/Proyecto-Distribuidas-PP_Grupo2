using System;
using System.Collections.Generic;
using System.Text;

namespace BancoSocket.ec.edu.espe.distribuidas.sockets.protocol
{
    class ProtocolException : SystemException
    {
        public ProtocolException(){

        }

        public ProtocolException(String mensaje)
        {
            mensaje = base.Message;
        }
    }
}
