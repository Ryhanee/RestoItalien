<?php

namespace App\Controller;

use App\Entity\Restaurant;
use App\Form\RestaurantType;
use App\Repository\CategorieRepository;
use App\Repository\MenuRepository;
use App\Repository\PlatsRepository;
use App\Repository\RestaurantRepository;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

class RestaurantsController extends AbstractController
{
    /**
     * index des restaurants
     * @Route("/restaurants/", name="resto_list", methods={"GET","HEAD"})
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
    public function show($libelle, RestaurantRepository $repos, MenuRepository $menu, PlatsRepository $plat, CategorieRepository $cat): Response
    {
        // trouver un restaurant a partirss d'une libelle
        $resto = $repos->findOneByLibelle($libelle);
        $menus = $menu->findAll($menu);
        $cats = $cat->findAll($cat);
        $plats = $plat->findAll();
        return $this->render('restaurants/show.html.twig',
            ['resto' => $resto,
                'menu' =>$menus,
                'cats' => $cats,
                'plat' => $plats
            ] );

    }

    /**
     * @Route("/restaurants", name="restaurants_search")
     * @return Response
     */
    //Recherche Resto
    public function search(Request $request, RestaurantRepository $repos): Response{
        $resto = $repos->findAll();
        if($request->getMethod("POST")){
            $em = $this->getDoctrine()->getManager();
            $motcle=$request->get('input_recherche');
            $query=$em->createQuery(
                "SELECT m FROM App\Entity\Restaurants m WHERE m.libelle LIKE '".$motcle."%'"
            );
            $resto=$query->getResult();
        }
        return $this->render('restaurants/index.html.twig',
            [
                'restau'=>$resto
            ]);
    }

    /**
     * @Route("/restaurants/list", name="restaurants")
     */
    public function showAll(RestaurantRepository $reposs, SessionInterface $session): Response
    {
        $resto = $reposs->findAll();
        return $this->render('restaurants/list.html.twig', [
            'restau' => $resto,
        ]);
    }

//ajouter un resto
    /**
     * Permet d'afficher un restaurant a partie de son nom
     * @Route ("/restaurants/add", name="add_resto")
     * * @return Response
     */
    public function add(Request $request, RestaurantRepository $repos): Response
    {
        $Modele = new Restaurant();
        $form = $this->createForm(RestaurantType::class, $Modele);

        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid())
        {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($Modele);
            $entityManager->flush();
            $this->addFlash('success', "Les données du restaurant ont élé enregistré avec succés !");

        }

        return $this->render('restaurants/add.html.twig',
            array(
                'Form'=>$form->createView()
            ));
    }

// supprimer un resto
    /**
     * Permet d'afficher un restaurant a partie de son nom
     * @Route ("/restaurants/delete/{id}", name="delete_resto")
     */
    public function deleteResto(Request $request, $id , RestaurantRepository $repos): Response
    {
        $resto = $repos->find($id);
        $em= $this->getDoctrine()->getManager();
        if ($resto!==null)
        {
            $em->remove($resto);
            $em->flush();
        }
        else
        {
            throw new NotFoundHttpException("Le restaurant d'id".$id."n'existe pas");
        }

        return $this->redirectToRoute("show_resto");
    }

//Mettre à jour un resto
    /**
     * Permet d'afficher un restaurant a partie de son nom
     * @Route ("/restaurants/update/{id}", name="update_resto")
     */
    public function update(Request $request, $id, RestaurantRepository $repos):Response
    {
        $resto = $repos->find($id);
        $editform = $this->createForm(RestaurantType::class, $resto);
        $editform->handleRequest($request);

        if ($editform->isSubmitted() && $editform->isValid())
        {
            $em = $this->getDoctrine()->getManager();
            $em->persist($resto);
            $em->flush();
            return $this->redirect($this->generateUrl("show_resto"));
        }

        return $this->render('restaurants/update.html.twig',
            [
                'editForm'=>$editform->createView()
            ]);
    }
    public function findAllOrdered()
    {
        $dql = 'SELECT plat FROM Entity\Categorie c, Entity\Plats p where p.';
    }


}
