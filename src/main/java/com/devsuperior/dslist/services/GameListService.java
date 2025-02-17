package com.devsuperior.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dslist.dto.GameListDTO;
import com.devsuperior.dslist.entities.GameList;
import com.devsuperior.dslist.projections.GameMinProjection;
import com.devsuperior.dslist.repositories.GameListRepository;
import com.devsuperior.dslist.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		List<GameListDTO> dto = result.stream().map(x -> new GameListDTO(x)).toList();
		return dto;
		// pode ser resumido e tirado a ultima linha colocando:
		// return result.stream().map(x -> new GameMinDTO(x)).toList();
		
	}
	

//	public void move(Long listId, int sourceIndex, int destinationPosition) {
//		
////		List<GameMinProjection> list = gameRepository.searchByList(listId); //busca a lista de jogos pelo id da lista>
////		
//////		//busca o jogo que esta sendo movido
//////		GameMinProjection obj = list.remove(sourceIndex);
//////		
//////		list.add(destinationPosition, obj);
//////		
//////		int min = Math.min(sourceIndex, destinationPosition);
//////		int max = Math.max(sourceIndex, destinationPosition);
//////		
//////		for (int i = min; i <= max; i++) {
//////			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
//////		} sugestão da ia a mesma lista já faz automaticamente o reposicionamento dos itens da lista
//		
//		
//			
//	};
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationPosition) {
		
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj = list.remove(sourceIndex); // separando o item a ser removido e movimentado na lista 
		
		list.add(destinationPosition, obj); // defininco a posição do item a ser movimentado
		
		int min = sourceIndex < destinationPosition ? sourceIndex : destinationPosition; // definindo o menor e o maior index
		int max = sourceIndex < destinationPosition ? destinationPosition : sourceIndex; // definindo o maior e o menor index
		
		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i); // atualizando os itens da lista
		}
	
	};

}
