<?php

namespace App\Entity;

use App\Repository\MenuRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=MenuRepository::class)
 */
class Menu
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;


    /**
     * @ORM\ManyToOne(targetEntity=Restaurant::class, inversedBy="menus")
     * @ORM\JoinColumn(name="id_resto", referencedColumnName="id_resto",nullable=false)
     */
    private $resto;

    /**
     * @ORM\OneToMany(targetEntity=Plats::class, mappedBy="menus")
     */
    private $plats;


    public function __construct()
    {
        $this->plats = new ArrayCollection();

    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getResto(): ?Restaurant
    {
        return $this->resto;
    }

    public function setResto(?Restaurant $resto): self
    {
        $this->resto = $resto;

        return $this;
    }

    /**
     * @return Collection|Plats[]
     */
    public function getPlats(): Collection
    {
        return $this->plats;
    }

    public function addPlat(Plats $plat): self
    {
        if (!$this->plats->contains($plat)) {
            $this->plats[] = $plat;
            $plat->setMenus($this);
        }

        return $this;
    }

    public function removePlat(Plats $plat): self
    {
        if ($this->plats->removeElement($plat)) {
            // set the owning side to null (unless already changed)
            if ($plat->getMenus() === $this) {
                $plat->setMenus(null);
            }
        }

        return $this;
    }

}
