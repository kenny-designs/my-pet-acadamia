SELECT player_pets.* pets.name
FROM player_pets
	INNER JOIN accounts
	ON player_pets.account_id = accounts.id
	INNER JOIN pets
	ON player_pets.pet_id = pets.id
WHERE accounts.id = 1;