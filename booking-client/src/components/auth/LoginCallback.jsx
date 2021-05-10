// import React, { useEffect } from 'react';
// import { withRouter } from 'react-router-dom';
// import PropTypes from 'prop-types';

// const LoginCallback = ({ location }) => {
//   useEffect(() => {
//     const code = (location.search.match(/code=([^&]+)/) || [])[1];
//     const state = (location.search.match(/state=([^&]+)/) || [])[1];
//     const qParams = [
//       `code=${code}`,
//       `redirect_uri=${
//         state === 'google' ? Google.REDIRECT_URI : Azure.REDIRECT_URI
//       }`,
//       `scope=${state === 'google' ? Google.SCOPE : Azure.SCOPE}`,
//     ].join('&');
//     fetch(`/api/auth-from-code/${state}?${qParams}`, {
//       credentials: 'include',
//     })
//       .then((res) => res.json())
//       .then((res) => console.log(res))
//       .catch(console.error);
//   }, []);

//   return (
//     <p>
//       {location.search}
//     </p>
//   );
// };

// LoginCallback.propTypes = {
//   location: PropTypes.arrayOf.isRequired,
// };

// export default withRouter(LoginCallback);
