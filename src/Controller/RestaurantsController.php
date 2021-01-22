<?php

namespace App\Controller;

use App\Entity\Restaurant;
use App\Repository\CategorieRepository;
use App\Repository\MenuRepository;
use App\Repository\PlatsRepository;
use App\Repository\RestaurantRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

class RestaurantsController extends AbstractController
{
    /**
     * @Route("/restaurants", name="restaurants")
     */
    public function index(RestaurantRepository $repos, SessionInterface $session): Response
    {
       // $repos = $this->getDoctrine()->getRepository(Restaurant::class);
        dump($session);
        $resto = $repos->findAll();
        return $this->render('restaurants/index.html.twig', [
            'restos' => $resto,
        ]);
    }

    /**
     * Permet d'afficher un restaurant a partie de son nom
     * @Route ("/restaurants/{libelle}", name="show_resto")
     * @return Response
     */
    public function show($libelle, RestaurantRepository $repos, MenuRepository $menu, PlatsRepository $plat, CategorieRepository $cat) {

        // trouver un restaurant a partirss d'une libelle
        $resto = $repos->findOneByLibelle($libelle);
        $menus = $menu->findAll($menu);
        $cats = $cat->findAll();
        $plats = $plat->findAll($cat);
        return $this->render('restaurants/show.html.twig',
       ['resto' => $resto,
           'menu' =>$menus,
           'cat' => $cats,
           'plat' => $plats
       ] );

    }
}
