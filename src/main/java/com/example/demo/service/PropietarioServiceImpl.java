package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.example.demo.repository.IPropietarioRepository;
import com.example.demo.repository.modelo.Propietario;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class PropietarioServiceImpl implements IPropietarioService{

	@Autowired
	private IPropietarioRepository propietarioRepository;

	@Override
	//@Transactional
	public void agregar(Propietario propietario) {
		// TODO Auto-generated method stub
		System.out.println("Service "+TransactionSynchronizationManager.isActualTransactionActive());
		this.propietarioRepository.insertar(propietario);
		
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public void actualizar(Propietario propietario) {
		// TODO Auto-generated method stub
		this.propietarioRepository.actualizar(propietario);
	}


	@Override
	public Propietario buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return this.propietarioRepository.seleccionarPorId(id);
	}

	@Override
	public void borrarPorId(Integer id) {
		// TODO Auto-generated method stub
		this.propietarioRepository.eliminarPorId(id);
	}

	@Override
	public List<Propietario> buscarTodos() {
		// TODO Auto-generated method stub
		return this.propietarioRepository.buscarTodos();
	}
	
}
