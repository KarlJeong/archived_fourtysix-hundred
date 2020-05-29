(function($) {
  fnShowSwal = async function(type, articleId, articleType) {
    'use strict';
    if (type === 'ban_article') {
        const { value: fruit } = await Swal.fire({
            title: 'Select field validation',
            input: 'select',
            inputOptions: {
              apples: 'Apples',
              bananas: 'Bananas',
              grapes: 'Grapes',
              oranges: 'Oranges'
            },
            inputPlaceholder: 'Select a fruit',
            showCancelButton: true,
            inputValidator: (value) => {
              return new Promise((resolve) => {
                if (value === 'oranges') {
                  resolve()
                } else {
                  resolve('You need to select oranges :)')
                }
              })
            }
          })

          if (fruit) {
            Swal.fire(`You selected: ${fruit}`)
          }
    }
  }

})(jQuery);