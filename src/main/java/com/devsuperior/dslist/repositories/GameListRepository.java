package com.devsuperior.dslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dslist.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {
	
	@Modifying
	@Query(nativeQuery = true,
	value = "UPDATE tb_belonging SET position = :newPosition WHERE list_id = :listId AND game_id = :gameId")
	void updateBelongingPosition(Long listId, Long gameId, Integer newPosition);
	
	
	/**
	 * Serve para atualizar a posição de um item dentro da tabela `tb_belonging` com base no ID da lista e do jogo.
	 * 
	 * Esta query SQL nativa modifica o valor da coluna `position` para um novo valor (`newPosition`)
	 * em um registro específico identificado pelos campos `list_id` e `game_id`.
	 * 
	 * @Modifying → Indica que a query realiza uma modificação nos dados (neste caso, um UPDATE).
	 * @Query(nativeQuery = true) → Especifica que a consulta utiliza SQL nativo diretamente no banco de dados.
	 * 
	 * Importante:
	 * - A anotação @Modifying exige que a operação seja realizada dentro de uma transação ativa.
	 * - Caso seja utilizada dentro de um serviço, o método chamador deve estar anotado com @Transactional.
	 * - O uso de SQL nativo pode ser útil para otimização, mas pode reduzir a portabilidade entre bancos de dados diferentes.
	 * 
	 * Exemplo de uso:
	 * belongingRepository.updateBelongingPosition(1L, 100L, 5);
	 * 
	 * Executará a seguinte consulta no banco de dados:
	 * UPDATE tb_belonging SET position = 5 WHERE list_id = 1 AND game_id = 100;
	 * 
	 * @param listId       ID da lista onde o jogo está localizado.
	 * @param gameId       ID do jogo cujo posicionamento será atualizado.
	 * @param newPosition  Nova posição a ser atribuída ao jogo na lista.
	 */



}
