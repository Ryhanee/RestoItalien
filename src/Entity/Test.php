<?php

namespace App\Entity;

use App\Repository\TestRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=TestRepository::class)
 */
class Test
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
    private $name;

    /**
     * @ORM\OneToMany(targetEntity=Image::class, mappedBy="name")
     */
    private $test2;

    public function __construct()
    {
        $this->test2 = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getName(): ?string
    {
        return $this->name;
    }

    public function setName(string $name): self
    {
        $this->name = $name;

        return $this;
    }

    /**
     * @return Collection|Image[]
     */
    public function getTest2(): Collection
    {
        return $this->test2;
    }

    public function addTest2(Image $test2): self
    {
        if (!$this->test2->contains($test2)) {
            $this->test2[] = $test2;
            $test2->setName($this);
        }

        return $this;
    }

    public function removeTest2(Image $test2): self
    {
        if ($this->test2->removeElement($test2)) {
            // set the owning side to null (unless already changed)
            if ($test2->getName() === $this) {
                $test2->setName(null);
            }
        }

        return $this;
    }
}
