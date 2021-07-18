import React from 'react';
import ReactRouterPropTypes from 'react-router-prop-types';
import { withRouter } from 'react-router-dom';

function NoMatch({ history }) {
  return (
    <div className="no-match">
      <div className="no-match__error-code">
        404
      </div>
      <div className="no-match__title">
        OOPS! NOTHING WAS FOUND
      </div>
      <div className="no-match__text">
        The page you are looking for might have been removed had its name changed
        or is temporarily unavailable
      </div>
      <div className="no-match__button">
        <button type="button" className="btn btn--home-page" aria-label="home-page" onClick={() => history.push('/')}>Take me to Home Page</button>
      </div>
    </div>
  );
}

NoMatch.propTypes = {
  history: ReactRouterPropTypes.history.isRequired,
};

export default withRouter(NoMatch);
