-- checks if the account has an associated safari_battle_instance with it
SELECT safari_battle_instances.id
FROM safari_battle_instances
	INNER JOIN team_instances
	ON safari_battle_instances.player_team_id = team_instances.id
	INNER JOIN battle_pets
	ON team_instances.battle_pet_1_id = battle_pets.id
	INNER JOIN player_pets
	ON battle_pets.player_pet_id = player_pets.id
WHERE player_pets.account_id = 1;