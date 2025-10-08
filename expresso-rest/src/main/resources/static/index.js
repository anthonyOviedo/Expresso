const navToggle = document.getElementById('navToggle');
const siteNav = document.getElementById('siteNav');
const body = document.body;

if (navToggle && siteNav) {
  navToggle.addEventListener('click', () => {
    const expanded = navToggle.getAttribute('aria-expanded') === 'true';
    navToggle.setAttribute('aria-expanded', String(!expanded));
    siteNav.classList.toggle('is-open');
  });

  siteNav.querySelectorAll('a').forEach(link => {
    link.addEventListener('click', () => {
      siteNav.classList.remove('is-open');
      navToggle.setAttribute('aria-expanded', 'false');
    });
  });
}

const currentPage = body?.dataset?.page;
if (currentPage) {
  const activeLink = document.querySelector(`[data-nav="${currentPage}"]`);
  if (activeLink) {
    activeLink.classList.add('is-active');
    activeLink.setAttribute('aria-current', 'page');
  }
}
