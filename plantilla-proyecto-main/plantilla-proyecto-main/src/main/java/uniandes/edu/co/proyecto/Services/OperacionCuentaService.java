package uniandes.edu.co.proyecto.Services;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import uniandes.edu.co.proyecto.Modelos.OperacionCuenta;

import uniandes.edu.co.proyecto.Repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.Repositorio.OperacionCuentaRepository;

@Service
public class OperacionCuentaService {

    // @Autowired
    // OperacionCuentaRepository operacionCuentaRepository;
    
    // public OperacionCuentaService(CuentaRepository cuentaRepository){
    // }
    
    // @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    // public Collection<OperacionCuenta> darCuentas() throws InterruptedException{
    //     Collection<OperacionCuenta> operaciones = operacionCuentaRepository.findAllOperacionCuentas();
    //     return operaciones;
    // }

    // @Transactional(isolation = Isolation.SERIALIZABLE)
    // public Collection<OperacionCuenta> darOperacionesSerializable(Integer id){
    //     Collection<OperacionCuenta> operaciones = null;
    //     try {
    //         Thread.sleep(30000);
    //         operaciones = operacionCuentaRepository.FiltrarPorC(id);
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    //     }
    //     return operaciones;
    // }

    // @Transactional(isolation = Isolation.READ_COMMITTED)
    // public Collection<OperacionCuenta> darOperacionesNoSerializable(Integer id){
    //     Collection<OperacionCuenta> operaciones = null;
    //     try {
    //         Thread.sleep(30000);
    //         operaciones = operacionCuentaRepository.FiltrarPorC(id);
    //     } catch (Exception e) {
    //         System.out.println(e.getMessage());
    //         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    //     }
    //     return operaciones;
    // }

}
