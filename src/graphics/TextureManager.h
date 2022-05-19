#ifndef _TextureManager_H
#define _TextureManager_H

#include <SDL.h>
#include <SDL_image.h>

#include "Renderer.h"

class TextureManager
{

public :
	/// <summary>
	/// Créé et retourne la texture chargée à partir d'un fichier.
	/// </summary>
	/// <param name="filename">nom du fichier</param>
	/// <returns>Texture</returns>
	static SDL_Texture* loadTexture(const char* filename);
	/// <summary>
	/// Dessine la texture sur la fenêtre au niveau du rectangle.
	/// </summary>
	/// <param name="texture">Texture à dessiner</param>
	/// <param name="src">position du rectangle</param>
	/// <param name="dest">position du rectangle</param>
	static void draw(SDL_Texture* texture, SDL_Rect src, SDL_Rect dest);
};

#endif