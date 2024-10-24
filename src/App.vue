<template>
    <HeaderSection></HeaderSection>

    <div class="lower-section">
      <FourDivs></FourDivs>
    </div>
</template>

<script>
  import HeaderSection from './HeaderSection.vue';
  import FourDivs from './FourDivs.vue';

  export default{
    components:{
      HeaderSection,
      FourDivs
    }
  }
</script>
