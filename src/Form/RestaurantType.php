<?php

namespace App\Form;

use App\Entity\Restaurant;
use Doctrine\DBAL\Types\TextType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RestaurantType extends AbstractType
{
    /**
     * @param FormBuilderInterface $builder
     * @param array $options
     * @param string $label
     * @param string $placeholder
     * @param string $options
     * @return array
     */
    private function getConfiguration($label , string $placeholder, $options = []){
        return array_merge([
            'label' => $label,
            'attr' => [ 'placholder' => $placeholder]
        ], $options);
    }
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('libelle', TextType::class, $this->getConfiguration("Libelle","Libelle"))
            ->add('speciality', TextType::class, $this->getConfiguration("Spécialité","spécialité"))
            ->add('telephone', TextType::class, $this->getConfiguration("Téléphone","téléphone"))
            ->add('Add Modele', SubmitType::class)
            ->add('livreurs',EntityType::class, array(
                'class' => 'RestoBundle\Entity\Emplacement',
                'choice_label'=>'adresse',
                'expanded'=>true,
                'multiple'=>true));
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Restaurant::class,
        ]);
    }
}
