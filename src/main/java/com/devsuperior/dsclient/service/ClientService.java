package com.devsuperior.dsclient.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dsclient.dto.ClientDTO;
import com.devsuperior.dsclient.model.Client;
import com.devsuperior.dsclient.repository.ClientRepository;
import com.devsuperior.dsclient.service.exception.DataIntegrityException;
import com.devsuperior.dsclient.service.exception.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = clientRepository.findAll(pageRequest);
		
		return list.map(client -> new ClientDTO(client));
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> opt = clientRepository.findById(id);
		
		Client client = opt.orElseThrow(() -> new ResourceNotFoundException("Recurso Buscado: " + id + ", "
											  + "Objeto Não Localizado: " + Client.class.getSimpleName()));
		
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO insert(ClientDTO dto) {
		Client client = new Client();
		copyDtoToClient(dto, client);
		
		client = clientRepository.save(client);

		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client client = clientRepository.getOne(id);
			copyDtoToClient(dto, client);

			client = clientRepository.save(client);
			
			return new ClientDTO(client);
			
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id Não Encontrado: " + id);
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id Número: " + id);
		
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma produto que possui produtos.");
		}
	}

	private void copyDtoToClient(ClientDTO dto, Client client) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
	}
}
