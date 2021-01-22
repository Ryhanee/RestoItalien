<?php

namespace App\Entity;

use App\Repository\CategorieRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CategorieRepository::class)
 */
class Categorie
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $libelle_cat;

    /**
     * @ORM\OneToMany(targetEntity=Plats::class, mappedBy="type", orphanRemoval=true)
     */
    private $libelle_plat;

    public function __construct()
    {
        $this->libelle_plat = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getLibelleCat(): ?string
    {
        return $this->libelle_cat;
    }

    public function setLibelleCat(string $libelle_cat): self
    {
        $this->libelle_cat = $libelle_cat;

        return $this;
    }

    /**
     * @return Collection|Plats[]
     */
    public function getLibellePlat(): Collection
    {
        return $this->libelle_plat;
    }

    public function addLibellePlat(Plats $libellePlat): self
    {
        if (!$this->libelle_plat->contains($libellePlat)) {
            $this->libelle_plat[] = $libellePlat;
            $libellePlat->setType($this);
        }

        return $this;
    }

    public function removeLibellePlat(Plats $libellePlat): self
    {
        if ($this->libelle_plat->removeElement($libellePlat)) {
            // set the owning side to null (unless already changed)
            if ($libellePlat->getType() === $this) {
                $libellePlat->setType(null);
            }
        }

        return $this;
    }
}
