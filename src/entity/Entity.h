#ifndef _Entity_H
#define _Entity_H

#include "GameObject.h"
#include <set>
#include <string>

class Entity : public GameObject
{
public:

	/// <summary>
	/// Id de l'entity.
	/// </summary>
	class Id {
	private:
		uint32_t m_idEntity;
		static uint32_t m_count;
	public:
		Id();
		bool operator==(Id const& id) const;
	};

private :
	//Status 
	enum class Status { RUNNING, DESTROY };	
	Status m_status;

	//Liste de toutes les entités du jeu
	static std::set<Entity*> m_entity;
	Id m_id;

	//Caractéristiques du l'entité.
	std::string m_name;


public :
	/// <summary>
	/// Constructeur
	/// </summary>
	/// <param name="map">Carte du jeu</param>
	/// <param name="textureSheet">Texture de l'entité</param>
	/// <param name="position">Position de l'entité</param>
	/// <param name="name">Nom de l'entité</param>
	Entity(Map* map, const char* textureSheet, const Vector2<int>& position, std::string name);
	virtual ~Entity();

	/// <summary>
	/// Getter
	/// </summary>
	/// <returns></returns>
	Status getStatus();
	/// <summary>
	/// Setter
	/// </summary>
	/// <param name="a_stat"></param>
	void setStatus(Status a_stat);

	/// <summary>
	/// Fonction à compléter permettant d'initaliser toutes les caractéristiques de l'entité.
	/// </summary>
	void initPersonnge();

};


#endif
