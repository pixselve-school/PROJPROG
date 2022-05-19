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

	//Liste de toutes les entit�s du jeu
	static std::set<Entity*> m_entity;
	Id m_id;

	//Caract�ristiques du l'entit�.
	std::string m_name;


public :
	/// <summary>
	/// Constructeur
	/// </summary>
	/// <param name="map">Carte du jeu</param>
	/// <param name="textureSheet">Texture de l'entit�</param>
	/// <param name="position">Position de l'entit�</param>
	/// <param name="name">Nom de l'entit�</param>
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
	/// Fonction � compl�ter permettant d'initaliser toutes les caract�ristiques de l'entit�.
	/// </summary>
	void initPersonnge();

};


#endif
