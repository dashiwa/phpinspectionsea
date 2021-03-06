<?php

    /* @var object      $object */
    /* @var string|null $mixed */

    /* @var string  $string */
    /* @var array   $array */
    /* @var boolean $boolean */
    /* @var float   $float */
    /* @var integer $integer */
    return [
        (object) $object,
        (string) $mixed,

        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(string)</weak_warning> $string,
        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(array)</weak_warning> $array,
        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(boolean)</weak_warning> $boolean,
        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(float)</weak_warning> $float,
        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(integer)</weak_warning> $integer,

        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(integer)</weak_warning> ($integer + 1),

        /* workaround for WI-37466 */
        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(float)</weak_warning> (0.99 * 1),
        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(float)</weak_warning> (1 * 0.99),
        (int)(1 * 0.99),
        (int)(1 * 0.99 * 1)
    ];

    /** @var string $string */
    function withWeakParameter($string) {
        return (string) $string;
    }
    function withStrictParameter(string $string) {
        return <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(string)</weak_warning> $string;
    }

    class ClassWithSomeMethods {
        /** @var string */
        private $private;
        /** @var string */
        protected $protected;

        /** @return string */
        function withWeakReturn() {
            return (string) $this->protected;
        }
        function withStrictReturn(): string {
            return <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(string)</weak_warning> $this->private;
        }
    }
    $instance = new ClassWithSomeMethods();
    return [
        (string) $instance->withWeakReturn(),
        <weak_warning descr="This type casting is not necessary, as the argument is of needed type.">(string)</weak_warning> $instance->withStrictReturn()
    ];