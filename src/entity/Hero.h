#ifndef _Hero_H
#define _Hero_H

#include "Entity.h"
#include <string>

class Hero :
    public Entity
{

public:
    /// <summary>
    /// Constructeur de Hero
    /// </summary>
    /// <param name="map">Carte</param>
    /// <param name="textureSheet">Texture du héro</param>
    /// <param name="position">Position initiale du héro</param>
    /// <param name="name">Nom du héro</param>
    Hero(Map* map, const char* textureSheet, const Vector2<int>& position, std::string name);
    virtual ~Hero();

    /// <summary>
    /// Fonction permettant de mettre à jour les caractéristiques du héro.
    /// </summary>
    void update();
};

#endif